package com.example

import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json.JsError
import sttp.client3._
import sttp.client3.testing.RecordingSttpBackend
import sttp.client3.testing.SttpBackendStub
import sttp.model.Method

class HttpBinClientTest extends AnyFunSuite {

  test("it can send a GET request") {
    val backend = new RecordingSttpBackend(
      SttpBackendStub.synchronous
        .whenRequestMatches { request =>
          request.method === Method.GET && request.uri.path.startsWith(List("get"))
        }
        .thenRespond(Right(HttpBinGetResponse(Map("q" -> "foo"), Map.empty, "https://httpbin.org/get?q=foo")))
    )

    val client = new HttpBinClient(backend)

    val response: Response[Either[ResponseException[String, JsError], HttpBinGetResponse]] = client.get(q = "foo")

    // verifying stub response
    assert(response.body.map(_.args) === Right(Map("q" -> "foo")))

    // verifying request
    val interactions = backend.allInteractions
    interactions match {
      case (request, _) :: Nil =>
        assert(request.uri === uri"https://httpbin.org/get?q=foo")
      case _ => fail()
    }
  }

}

package com.example
import play.api.libs.json.JsError
import sttp.client3._
import sttp.client3.playJson._

class HttpBinClient(backend: SttpBackend[Identity, Any]) {

  def get(q: String): Response[Either[ResponseException[String, JsError], HttpBinGetResponse]] = basicRequest
    .get(uri"https://httpbin.org/get?q=$q")
    .response(asJson[HttpBinGetResponse])
    .send(backend)

  def post(data: Map[String, String]): Response[Either[ResponseException[String, JsError], HttpBinPostResponse]] = basicRequest
    .post(uri"https://httpbin.org/post")
    .body(data)
    .response(asJson[HttpBinPostResponse])
    .send(backend)

}

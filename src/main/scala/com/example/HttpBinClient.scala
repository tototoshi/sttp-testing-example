package com.example
import play.api.libs.json.JsError
import sttp.client3._
import sttp.client3.playJson._

class HttpBinClient(backend: SttpBackend[Identity, Any]) {

  def get(q: String): Response[Either[ResponseException[String, JsError], HttpBinGetResponse]] = basicRequest
    .get(uri"https://httpbin.org/get?q=$q")
    .response(asJson[HttpBinGetResponse])
    .send(backend)

}

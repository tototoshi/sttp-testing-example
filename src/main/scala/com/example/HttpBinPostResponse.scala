package com.example

import play.api.libs.json.JsError
import play.api.libs.json.Json
import play.api.libs.json.Reads

case class HttpBinPostResponse(form: Map[String, String], headers: Map[String, String], url: String)

object HttpBinPostResponse {
  given Reads[HttpBinPostResponse] = Json.reads[HttpBinPostResponse]
}

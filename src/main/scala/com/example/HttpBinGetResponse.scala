package com.example

import play.api.libs.json.JsError
import play.api.libs.json.Json
import play.api.libs.json.Reads

case class HttpBinGetResponse(args: Map[String, String], headers: Map[String, String], url: String)

object HttpBinGetResponse {
  given Reads[HttpBinGetResponse] = Json.reads[HttpBinGetResponse]
}

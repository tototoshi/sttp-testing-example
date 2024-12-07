package com.example

import sttp.client3.HttpClientSyncBackend
import sttp.model.StatusCode

object Main {

  def main(args: Array[String]): Unit = {
    val backend = HttpClientSyncBackend()

    val client = new HttpBinClient(backend)

    val response = client.get(q = "foo")

    assert(response.code == StatusCode.Ok)
    assert(response.body.map(_.url) == Right("https://httpbin.org/get?q=foo"))
    assert(response.body.map(_.args) == Right(Map("q" -> "foo")))
  }

}

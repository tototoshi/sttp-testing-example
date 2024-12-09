package com.example

import sttp.client3.HttpClientSyncBackend
import sttp.model.StatusCode

object Main {

  def testGet(client: HttpBinClient): Unit = {
    val response = client.get(q = "foo")

    assert(response.code == StatusCode.Ok)
    assert(response.body.map(_.url) == Right("https://httpbin.org/get?q=foo"))
    assert(response.body.map(_.args) == Right(Map("q" -> "foo")))
  }

  def testPost(client: HttpBinClient): Unit = {
    val response = client.post(Map("foo" -> "bar"))

    assert(response.code == StatusCode.Ok)
    assert(response.body.map(_.url) == Right("https://httpbin.org/post"))
    assert(response.body.map(_.form) == Right(Map("foo" -> "bar")))
  }

  def main(args: Array[String]): Unit = {
    val backend = HttpClientSyncBackend()

    val client = new HttpBinClient(backend)

    testGet(client)
    testPost(client)
  }

}

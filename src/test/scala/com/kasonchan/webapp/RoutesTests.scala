package com.kasonchan.webapp

import com.kasonchan.routes._
import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike

class RoutesTest extends ScalatraSuite with FunSuiteLike {
  // `HelloWorldServlet` is your app which extends ScalatraServlet
  addServlet(classOf[Routes], "/*")

  test("simple get") {
    get("/path/to/something") {
      status should equal (404)
      body should include ("Not found")
    }
  }
}
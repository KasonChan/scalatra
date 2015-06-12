package com.kasonchan.controllers

import org.scalatra._
import scalate.ScalateSupport
import com.kasonchan.models.ColorData

// JSON-related libraries
import org.json4s.{ DefaultFormats, Formats }

// JSON handling support from Scalatra
import org.scalatra.json._

class WebAppServlet extends WebappStack with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say<a href="hello-scalate">hello to Scalate</a>
        .
      </body>
    </html>
  }

  get("/hello") {
    <html>
      <body>
        Hello
      </body>
    </html>
  }

  get("/colors") {
    ColorData.all
  }

}

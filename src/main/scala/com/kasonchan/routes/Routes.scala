package com.kasonchan.routes

import org.scalatra._
import scalate.ScalateSupport
import com.kasonchan.models.{ ColorData, File, Message, PersonData }

// JSON-related libraries
import org.json4s.{ DefaultFormats, Formats }

// JSON handling support from Scalatra
import org.scalatra.json._

class Routes extends WebappStack with JacksonJsonSupport {

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

  get("/colors") {
    ColorData.all
  }

  get("/colors/:color") {
    { params("color") } match {
      case "red" => ColorData.red
      case "orange" => ColorData.orange
      case "yellow" => ColorData.yellow
      case "green" => ColorData.green
      case "blue" => ColorData.blue
      case "purple" => ColorData.purple
      case _ =>
        val message = Message("Not found")
        NotFound(message)
    }
  }

  /**
   * Named parameters
   */
  get("/hello/:name") {
    { params("name") } match {
      case "kasonchan" => PersonData.kasonchan
      case "webapp" => PersonData.webapp
    }
  }

  /**
   * Wildcards
   */
  get("/hello/*/*") {
    // Matches "GET /say/hello/to/world"
    multiParams("splat") // == Seq("hello", "world")
  }

  get("/download/*.*") {
    // Matches "GET /download/path/to/file.xml"
    val file = multiParams("splat")
    File(file.head, file.last) // == Seq("path/to/file", "xml")
  }

}

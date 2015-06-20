package com.kasonchan.routes

import org.scalatra._
import org.json4s.JValue
import scalate.ScalateSupport
import com.kasonchan.models.{ ColorData, File, Message, PersonData, Person }

// JSON-related libraries
import org.json4s.{ DefaultFormats, Formats }

// JSON handling support from Scalatra
import org.scalatra.json._

class Routes extends WebappStack with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("*") {
    val message = Message(Seq("Not found", "I don't know"))
    NotFound(message)
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
        val message = Message(Seq("Not found"))
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
      case _ =>
        val message = Message(Seq("Not found"))
        NotFound(message)
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

  protected override def transformRequestBody(body: JValue): JValue = body.camelizeKeys

  protected override def transformResponseBody(body: JValue): JValue = body.underscoreKeys

  post("/create") {
    val p = parsedBody.extract[Person]
    Message(Seq(p.login, p.name, p.email))
  }

  post("/createmaybe") {
    val p = request.body
    Message(Seq(p))
  }

}

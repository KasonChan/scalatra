package com.kasonchan.controllers

import org.scalatra._
import org.json4s.JValue
import com.kasonchan.routes.WebappStack
import com.kasonchan.models.{ ColorData, File, Message, PersonData, Person }

// MongoDb-specific imports
import com.mongodb.casbah.Imports.MongoCollection

// JSON-related libraries
import org.json4s.{ DefaultFormats, Formats }

// JSON handling support from Scalatra
import org.scalatra.json._

class MongoController(mongoColl: MongoCollection) extends WebappStack with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  protected override def transformRequestBody(body: JValue): JValue = body.camelizeKeys

  protected override def transformResponseBody(body: JValue): JValue = body.underscoreKeys

  get("*/?") {
    val message = Message(Seq("Not found"))
    NotFound(message)
  }

  // /**
  //  * Insert a new object into the database. You can use the following from your console to try it out:
  //  * curl -i -H "Accept: application/json" -X POST -d "key=super&value=duper" http://localhost:8080/insert
  //  */
  // post("/") {
  //   val key = params("key")
  //   val value = params("value")
  //   val newObj = MongoDBObject(key -> value)
  //   mongoColl += newObj
  // }

  /**
   * Retrieve everything in the MongoDb collection we're currently using.
   */
  get("/test/?") {
    mongoColl.find()
    val result = for { x <- mongoColl } yield x
    val message = Message(Seq(result.toString))
    Ok(message)
  }

  // /**
  //  * Query for the first object which matches the values given. If you copy/pasted the insert example above,
  //  * try http://localhost:8080/query/super/duper in your browser.
  //  */
  // get("/query/:key/:value") {
  //   val q = MongoDBObject(params("key") -> params("value"))
  //   for ( x <- mongoColl.findOne(q) ) yield x
  // }
  // 

  before() {
    contentType = formats("json")
  }

}
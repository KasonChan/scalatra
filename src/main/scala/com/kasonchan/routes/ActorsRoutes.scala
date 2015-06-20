package com.kasonchan.routes

import akka.actor.{ Actor, ActorRef, ActorSystem }
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

import org.scalatra._
import org.json4s.JValue
import scalate.ScalateSupport

// JSON-related libraries
import org.json4s.{ DefaultFormats, Formats }

// JSON handling support from Scalatra
import org.scalatra.json._

class ActorsRoutes(system: ActorSystem, actor: ActorRef) extends ScalatraServlet {

  implicit val timeout = new Timeout(2 seconds)
  protected implicit def executor: ExecutionContext = system.dispatcher

  // Tell the actor you know what
  get("/tell") {
    actor ! "Hey, you know what?"
    Accepted()
  }

}
package com.kasonchan.actors

import _root_.akka.actor.Actor

class Worker extends Actor {

  def receive = {
    case s: String =>
      println(s)
    case i: Int =>
      println(i)
    case _ => println("I don't understand")
  }

}

package com.kasonchan.models

case class Color(slug: String, name: String)

object ColorData {

  val all = List(
    Color("blue", "Blue"),
    Color("yellow", "Yellow"))

}
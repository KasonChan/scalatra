package com.kasonchan.models

case class Color(slug: String, name: String)

object ColorData {

  val red = Color("red", "Red")

  val orange = Color("orange", "Orange")

  val yellow = Color("yellow", "Yellow")

  val green = Color("green", "Green")

  val blue = Color("blue", "Blue")

  val purple = Color("purple", "Purple")

  val all = List(red,
    orange,
    yellow,
    green,
    blue,
    purple)

}
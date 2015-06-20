package com.kasonchan.models

case class Person(login: String, name: String, email: String)

case class PersonMaybe(login: Option[String], name: Option[String], email: Option[String])

object PersonData {

  val kasonchan = Person("kasonchan", "kasonchan", "kasonchan@kasonchan.com")

  val webapp = Person("webapp", "webapp", "webapp@webapp.com")

  val all = List(kasonchan, webapp)

}
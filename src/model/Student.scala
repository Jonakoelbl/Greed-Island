package model

case class Student (aName: String, aLastname: String, anAge: Int, currentCareer: Career)
  extends Person(aName, aLastname, anAge) with Schedule {

  type Assist = Boolean
  type Note = Int
  var courses: List[(Course, Assist, Note)] = List() //Should not have repeated
}
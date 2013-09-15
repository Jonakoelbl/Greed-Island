package model

class AcademicRecord {

  type Assist = Boolean
  type Note = Int
  
  def averageWithFails(student: Student): Double = {
    var value: Double = 0
    val coursesWithFails: List[(Course, Assist, Note)] = student.courses filter (p => p._3 < 4)
    coursesWithFails foreach (s => value += s._3)
    value /= coursesWithFails.length
    value
  }

  def averageWithoutFails(student: Student): Double = {
    var value: Double = 0
    val coursesWithFails: List[(Course, Boolean, Int)] = student.courses filter (p => p._3 >= 4)
    coursesWithFails foreach (s => value += s._3)
    value /= coursesWithFails.length
    value
  }

  def amountSuccessful(student: Student): Int = student.courses filter (s => s._3 >= 4) length
  def amountFailed(student: Student): Int = student.courses filter (s => !s._2) length // Student did not attend 

  def averageApprovedSubjects(student: Student): Int = {
    student.courses.length / amountSuccessful(student)
  }

  def notesTable(student: Student): Map[Int, Int] = {
    var ms: Map[Int, Int] = Map()
    for (k <- (10 until 0)) {
      ms += (k -> (student.courses.filter (s => s._3 == k) length))
    }
    ms
  }

  def bestNoteNTimes(n: Int, student: Student) = {
    var x: Int = 0
    for (notes <- notesTable(student)) {
      x += notes._2
      if (x >= n)
        notes._1
    }
  }

  //This finds the best note >= n
  def bestNoteNTimes2(n: Int, student: Student) = {
    var x: Int = 0
    for (notes <- notesTable(student)) {
      x = notes._2
      if (x >= n)
        notes._1
    }
  }

}
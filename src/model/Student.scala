package model

case class Student (aName: String, aLastname: String, anAge: Int, currentCareer: Career)
  extends Person(aName, aLastname, anAge) with Schedule {

  type Assist = Boolean
  type Note = Int
  var courses: List[(Course, Assist, Note)] = List() //Should not have repeated
  
  def averageWithFails : Double ={ 
    val notesWithFails : List[(Course, Assist, Note)]= courses filter (p => p._3 < 4)
    notesWithFails.foldLeft(0.0)( _ + _._3) / notesWithFails.length
  }
  
   def averageWithoutFails: Double = {
    val coursesWithoutFails: List[(Course, Boolean, Int)] = courses filter (p => p._3 >= 4)
    coursesWithoutFails.foldLeft(0.0)(_ + _._3) / coursesWithoutFails.length
  }
   
  def amountSuccessful: Int = courses filter (s => s._3 >= 4) length
  
  def amountFailed: Int = courses filter (s => !s._2) length // Student did not attend 
  
  def averageApprovedSubjects: Int = courses.length / this.amountSuccessful

  def notesTable: List[(Int, Int)] = {//(Note, total)
    def table(n: Int): List[(Int, Int)] = { //Local function
      if (n < 0) List()
      else (n, courses filter (s => s._3 == n) length) :: table(n - 1)}
    table(10)
  }
  
  def bestNoteNTimes(n: Int): Int ={
    (notesTable find(e => e._2 >= n))  
    match {
		case Some(y)=> y._1
		case None  	=> 0
    } 
  }
}
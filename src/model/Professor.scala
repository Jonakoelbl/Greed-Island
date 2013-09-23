package model

case class Professor (aName: String, aLastname: String, anAge: Int)
	extends Person (aName, aLastname, anAge) with Schedule with AcademicQuery{

  def getAcademicRecord ={
    val allCourses = AcademicData.Students.foldRight(List[(Course, Boolean, Int)]())((a, b) => a.courses ++ b)
    allCourses.filter(p => p._1.professors.equals(this))//
    		  .foldRight(List[(Boolean, Int)]())((a, b) => (a._2, a._3) :: b) 
  }
}
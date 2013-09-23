package model

import java.util.Date

class Course (val subject: Subject,val professors: List[Professor],val students: List[Student], implicit val aDate : Date = new Date)
		extends WithClassRoom with AcademicQuery{
  
  def neededSpace : Int = {
    students.length + professors.length 
  }

  def getAcademicRecord ={
    val allNotes = students.foldRight(List[(Course, Boolean, Int)]())(_.courses ++ _).filter(c=> c._1.subject.aName.equals(this.subject.aName))
    allNotes.foldRight(List[(Boolean, Int)]())((a, b) => (a._2, a._3)::b)
  }
}

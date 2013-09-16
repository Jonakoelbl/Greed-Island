package model

import java.util.Date

class Course (subject: Subject, professors: List[Professor], students: List[Student], implicit val aDate : Date = new Date)
		extends WithClassRoom {
  def neededSpace : Int = {
    students.length + professors.length 
  }
}

package model

import java.sql.Time
import java.util.Date

abstract class Person (aName: String, aLastname: String, anAge: Int) {

}

trait Schedule {
  type Schedule = List[Event]
  var schedule : Schedule = List()
  var belongProjects : List[Activity] = List()
  
//  def defineSchedule = {
//    for (p <- belongProjects){
//      new Activity () with Event
//    }
//  }
}
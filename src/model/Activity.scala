package model

import java.util.{Date, Calendar}
import java.sql.Time

abstract class Activity (val aName: String, aResponsible: Person, members: List[Person], val financialAmount: Int) {
  
	var approved: Boolean = false
	implicit val filingDate: Date = Calendar.getInstance().getTime()
	var approvalDate = new Date
	
	def approve(){
	  approved = true
	  approvalDate = Calendar.getInstance().getTime()
	}
	
	def isApproved: Boolean = {
	  approved
	}
	
}

case class Seminary (
						override val aName: String, 
						aResponsible: Person,
						members: List[Person],
						override val financialAmount: Int, 
						sessions: List[Session]
					) 
	extends Activity (aName, aResponsible, members, financialAmount) {
  
  def neededSpace : Int = members length
}

case class Project (
						override val aName: String,
						aResponsible: Person,
						members: List[Person],
						override val financialAmount: Int,
						description : String,
						var results : List[Result],
						var log : List[Experiment]
					) 
	extends Activity (aName, aResponsible, members, financialAmount) {
	
}

case class Talk (
					override val aName: String,
					aResponsible: Person,
					members: List[Person with Schedule],
					estimatedPublic: Int,
					override val financialAmount: Int,
					aDate: Date,
					fromHour: Time,
					toHour: Time 
				) 
	extends Activity (aName, aResponsible, members, financialAmount) with Event {
	
  def neededSpace : Int = members.length + estimatedPublic
  
  implicit def scheduleMembers = {
    for (m <- members) {
      this :: m.belongProjects
    }
  }
}

case class Result (aDate: Date, description: String)

case class Session (aDate: Date, fromHour: Time, toHour: Time) extends Event

case class Experiment (aDate: Date, fromHour: Time, toHour: Time, description: String) extends Event

trait Event {
  def aDate: Date
  def fromHour: Time
  def toHour: Time
//  def description: String
}
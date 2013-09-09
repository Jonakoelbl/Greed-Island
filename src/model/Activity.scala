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
  
}

case class Project (
						override val aName: String,
						aResponsible: Person,
						members: List[Person],
						override val financialAmount: Int
					) 
	extends Activity (aName, aResponsible, members, financialAmount) {
	
}

case class Talk (
					override val aName: String,
					aResponsible: Person,
					members: List[Person],
					override val financialAmount: Int,
					aDate: Date,
					fromHour: Time,
					toHour: Time
				) 
	extends Activity (aName, aResponsible, members, financialAmount) {

}
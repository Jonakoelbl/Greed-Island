package model

import java.util.{Date, Calendar}
import java.sql.Time

abstract class Activity (aName: String, aResponsible: Person, members: List[Person], financialAmount: Int) {
  
	var approved: Boolean = false
	implicit val filingDate: Date = Calendar.getInstance().getTime()
	var approvalDate = new Date
	
	def approve(){
	  approved = true
	  approvalDate = Calendar.getInstance().getTime()
	}
}

case class Seminary (
						aName: String, 
						aResponsible: Person,
						theMembers: List[Person],
						financialAmount: Int, 
						sessions: List[Session]
					) 
	extends Activity (aName, aResponsible, theMembers, financialAmount) {
  
}

case class Project (
						aName: String,
						aResponsible: Person,
						theMembers: List[Person],
						financialAmount: Int
					) 
	extends Activity (aName, aResponsible, theMembers, financialAmount) {
	
}

case class Talk (
					aName: String,
					aResponsible: Person,
					theMembers: List[Person],
					financialAmount: Int,
					aDate: Date,
					fromHour: Time,
					toHour: Time
				) 
	extends Activity (aName, aResponsible, theMembers, financialAmount) {

}
import java.util.{Date, Calendar}

abstract class Activity (aName: String, aResponsible: Person, theMembers: List[Person], financialAmount: Int) {
	val name = aName
	var responsible = aResponsible
	var members = theMembers
	val totalFinancialAmount = financialAmount
  
	var approved: Boolean = false
	implicit val filingDate: Date = Calendar.getInstance().getTime()
	var approvalDate = new Date
	
	def approve(){
	  approved = true
	  approvalDate = Calendar.getInstance().getTime()
	}
}
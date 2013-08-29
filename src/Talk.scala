import java.util.Date
import java.sql.Time

class Talk (aName: String, aResponsible: Person, theMembers: List[Person], financialAmount: Int, aDate: Date, fromHour: Time, toHour: Time) 
	extends Activity (aName, aResponsible, theMembers, financialAmount) {

}
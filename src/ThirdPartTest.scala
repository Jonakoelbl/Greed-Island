package model

import org.scalatest.FunSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.util.Date
import java.sql.Time
import org.scalatest.time.Hour

class ThirdPartTest extends FlatSpec with ShouldMatchers with MockitoSugar  {
	
	def fixture = new {
	  val mockMembers = mock[List[Person]]
	  val mockSession = mock[List[Session]]
	  val mockExperiments = mock[List[Experiment]]
	  val group = new Group(mockMembers)
	  val responsible = new Professor("Cosme", "Fulanito", 32)
	}
	
	"Group" should "have three unapproved projects" in {
	  val f = fixture
	  val mockDate = mock[Date]
	  val mockTime = mock[Time]
	  val mockMembersWithSchedule = mock[List[Person with Schedule]]
	  val proyectApproved = new Project("Non so", f.responsible, f.mockMembers, 23, "non capisco", List(), List())
	  proyectApproved.approve
	  f.group presentedActivities = (new Seminary("Program", f.responsible,f.mockMembers, 24, f.mockSession))::
	  								(new Project("Butterfly", f.responsible, f.mockMembers, 23, "I haven't idea", List(), f.mockExperiments))::
	  								(new Talk("Lambda",f.responsible,mockMembersWithSchedule , 12, 26, mockDate, mockTime, mockTime))::
	  								(proyectApproved)::List()
	  
	  val nonApprovedActivities = f.group.nonApprovedActivities
	  
	  nonApprovedActivities should have length (3)
	  nonApprovedActivities should ( equal ((new Seminary("Program", f.responsible,f.mockMembers, 24, f.mockSession))::
	  										(new Project("Butterfly", f.responsible, f.mockMembers, 23, "I haven't idea", List(), f.mockExperiments))::
	  										(new Talk("Lambda",f.responsible,mockMembersWithSchedule , 12, 26, mockDate, mockTime, mockTime))
	  										::List()))
	}
	
	it should "have total amount of 100" in {
	  val f = fixture
	  val mockDate = mock[Date]
	  val mockTime = mock[Time]
	  val mockMembersWithSchedule = mock[List[Person with Schedule]]
	  f.group presentedActivities = (new Seminary("Program", f.responsible,f.mockMembers, 24, f.mockSession))::
	  								(new Project("Butterfly", f.responsible, f.mockMembers, 12, "I haven't idea", List(),f.mockExperiments))::
	  								(new Talk("Lambda",f.responsible,mockMembersWithSchedule , 12, 64, mockDate, mockTime, mockTime))::List()
	  								
	  f.group.nonApprovedTotalAmount should be (100)
	}
	
	it should "give tree activity" in {
	  val f = fixture 
	  val mockDate = mock[Date]
	  val mockTime = mock[Time]
	  val mockMembersWithSchedule = mock[List[Person with Schedule]]
	  val log = (new Experiment(mockDate, mockTime, mockTime, "Article"))::List()
	  f.group presentedActivities = (new Seminary("Article Program", f.responsible,f.mockMembers, 24, f.mockSession))::
	  								(new Project("Butterfly", f.responsible, f.mockMembers, 12, "I haven't idea", List(), f.mockExperiments))::
	  								(new Talk("Article Lambda",f.responsible,mockMembersWithSchedule , 12, 64, mockDate, mockTime, mockTime))::
	  								(new Project("Night", f.responsible, f.mockMembers, 13, "I have dream",List(), log))::List()
	  
	  f.group.presentedActivities foreach ( p => p.approved)
	  
	  val publishedArticles =  f.group.publishedArticles
	  
	  publishedArticles should have length (3)
	  publishedArticles should (equal ((new Seminary("Article Program", f.responsible,f.mockMembers, 24, f.mockSession))::
	  								   (new Talk("Article Lambda",f.responsible,mockMembersWithSchedule , 12, 64, mockDate, mockTime, mockTime))::
	  								   (new Project("Night", f.responsible, f.mockMembers, 13, "I have dream",List(), log))::List()))
	}
}

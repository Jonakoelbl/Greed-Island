class Group (members: List[Person]) {
	var presentedActivities: List[Activity] = null

	def nonApprovedActivities : List[Activity] = presentedActivities filter(activity => (!activity.approved)) 
	
	def nonApprovedTotalAmount : Int ={
		var value:Int = 0
		this.nonApprovedActivities foreach(activity => value += activity.financialAmount)
		value
	}
	
	def publishedArticles : List[(Activity, Date)] = {
	  var articles: List[(Activity, Date)] = null
	  presentedActivities filter(a => (a.aName contains("article"))) foreach(activity => (activity, activity.approvalDate)::articles)
	  articles
	}
	
}

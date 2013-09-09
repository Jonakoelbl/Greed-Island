class Student (aName: String, aLastname: String, anAge: Int) 
	extends Person (aName, aLastname, anAge) { 
	//Subject, Boolean=Assist, Int=Note
	var subjects : List[(Subject, Boolean, Int)] = null//Should not have repeated
	
	def averageWithFails : Double ={
	  var value: Double = 0 
	  val subjectsWithFails: List[(Subject, Boolean, Int)] = subjects filter(p => p._3 < 4) 
	  subjectsWithFails foreach(s => value += s._3 )
	  value /= subjectsWithFails.length
	  value
	}
	def averageWithoutFails:Double ={
	 var value: Double = 0
	 val subjectsWF: List[(Subject, Boolean, Int)] = subjects filter(p =>p._3 >= 4)
	 subjectsWF foreach(s => value += s._3)
	 value /= subjectsWF.length
	 value
	}

	def amountSuccessful : Int = subjects filter(s => s._3 >= 4) length
	def amountFailed : Int = subjects filter(s => s._2) length // Student did not attend
	
	//def porcentaje de cursos aprobados sobre cursos iniciados
	
	def notesTable : Map[Int, Int] ={
	  var ms: Map[Int, Int] = null
	  for(k <- (0 until 10)){
	    ms += (k -> (subjects filter(s => s._3 == k) length))
	  }
	  ms
	}
	
	//def bestNoteNTimes(n: Int) {
	  
	//}
}

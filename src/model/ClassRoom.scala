package model

class ClassRoom (var resources: List[Resource], val space: Int, val boardSize: Int)

class Resource (val aName: String, val size: Int)

trait Size {
  //TODO
}

trait Resources {
	var resources : List[Resource] = List()
}

trait WithClassRoom extends Resources {
  def neededSpace : Int
  
  def itFit(room: ClassRoom): Boolean = {
    room.space >= neededSpace
  }
  
  def haveEnoughResources(room: ClassRoom) : Boolean = {
    resources forall(r => room.resources.contains(r) && room.boardSize >= r.size) 
  }
}

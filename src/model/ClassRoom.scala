package model

//TODO
class ClassroomManagement (val events : List[_ with WithClassRoom]) {
  var busyClassroom: List[(ClassRoom, _ with WithClassRoom)] = List()
  
  def assingClassroom(classrooms: List[ClassRoom]) ={
    
  }
}

class ClassRoom(var resources: List[Resource], val space: Int)

class Resource(val aName: String)

trait Size {
  def size: Int
}

trait Resources {
  var resources: List[Resource] = Nil
}

trait WithClassRoom extends Resources {
  def neededSpace: Int

  def itFit(room: ClassRoom): Boolean = {
    room.space >= neededSpace && resources.forall (r => room.resources.contains(r))
  }

  def haveEnoughResources(room: ClassRoom): Boolean = {
    resources forall (r => room.resources.contains(r))
  }
}

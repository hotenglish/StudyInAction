package chapter4

class Person {

  case class Worker() extends Person

  case class Student() extends Person

  def matchType(p: Person) {
    p match {
      case stu: Student => println("I am a student" + stu)
      case worker: Worker => println("I am a worker" + worker)
      case _=> println("Nothing")
    }
  }

}

package chapter4

sealed class PersonSealExample {

  case class Worker() extends PersonSealExample

  case class Student() extends PersonSealExample

  def matchType(p: PersonSealExample) {
    p match {
      case stu:Student=>println("I am a student" + stu)
      case worker:Worker=>println("I am a worker" + worker)
      case _ => println("Nothing")
    }
  }

}

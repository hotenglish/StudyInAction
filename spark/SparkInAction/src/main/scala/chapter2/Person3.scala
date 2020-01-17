package chapter2

trait Person3 {
  val name: String
  val age = 50
}

trait Worker3 {
  val age = 25
}

class Student4 extends Person3 with Worker3 {
  val name: String = " Zhangsan"
  override val age = 15
}

object StudentTest2 {

  def main(args: Array[String]) {
    val s = new Student4
    print("Name is:" + s.name + ",age is:" + s.age)
  }

}

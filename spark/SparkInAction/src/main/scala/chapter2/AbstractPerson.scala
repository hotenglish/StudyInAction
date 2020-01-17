package chapter2

abstract class AbstractPerson(name: String) {
  println(this.name)
  private var age = 20
  var sex: String = "male"
}

class Student1 extends AbstractPerson("lisi")

object AbstractPersonTest {

  def main(args: Array[String]) {
    val student = new Student1
    println(student.sex)
  }

}



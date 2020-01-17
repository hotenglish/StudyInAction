package chapter2

trait Person2 {
  def eat(str: String) = {
    println(str)
  }
}

trait Worker {
  def work: Unit = {
    println("Working...")
  }
}

class Student3 extends Worker with Person2

object TestPerson2 {

  def main(args: Array[String]): Unit = {
    val student = new Student3
    student.eat("Have lunch...")
    student.work
  }

}



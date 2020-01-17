package chapter2

class Student(name: String, age: Int, sex: String) {
  println(name + ":" + age + ":" + sex)
}

object StudentTest {
  def main(args: Array[String]) {
    val s = new Student("zhangan", 25, "male")
  }
}

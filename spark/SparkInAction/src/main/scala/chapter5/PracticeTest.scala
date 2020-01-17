package chapter5

object PracticeTest {

  def main(args: Array[String]) {

  }

  implicit class Calc(x: Int) {
    def add(a: Int) = {
      a + x
    }
  }

  println("1.add(2)=" + 1.add(2))

}

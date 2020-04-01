package chapter3

object MyCurring {

  def main(args: Array[String]): Unit = {
    def funCurring(x: Int) = (y: Int) => (z: Int) => x * y + z

    var step1 = funCurring(3)
    var step2 = step1(3)
    var step3 = step2(5)

    println(step3)

    println(funCurring(5)(6)(7))
  }

}

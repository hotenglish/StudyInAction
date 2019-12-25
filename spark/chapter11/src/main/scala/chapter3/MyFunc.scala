package chapter3

object MyFunc {

  def max(x: Int, y: Int) = if (x > y) x else y

  def main(args: Array[String]): Unit = {
    println(max(123, 232))
    println("Hellow world!")
  }

}

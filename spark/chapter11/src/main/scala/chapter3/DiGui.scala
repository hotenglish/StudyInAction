package chapter3

object DiGui {

  def main(args: Array[String]): Unit = {
    println(funFactorial(4))
  }

  def funFactorial(n: Int): Int = {
    if (n == 0) return 1
    return n * funFactorial(n - 1)
  }
}

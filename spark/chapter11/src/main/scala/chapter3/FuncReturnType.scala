package chapter3

object FuncReturnType {

  def main(args: Array[String]): Unit = {
    println(add(2, 6))
    returnUnit()
  }

  def add(x: Int, y: Int): Int = {
    x + y
  }

  def returnUnit(): Unit = {
    println("another way to return void")
  }

}

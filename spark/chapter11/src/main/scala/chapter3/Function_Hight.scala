package chapter3

object Function_Hight {

  def func1(n: Int): Int = {
    val myFunc = (i: Int, x: Int) => i * x
    myFunc.apply(n, 2)
  }

  def func2(x: Int => Int) = x

  def func3(f: (Int, Int) => Int) = f

  val func4 = (x: Int) => x + 1

  val func6 = (_: Int) + (_: Int)

  def func7(a: Int, b: Int, c: Int) = a + b + c

  val func8 = func7 _

  val func9 = func7(1, _: Int, 3)

  def func10(x: Int) = (y: Int) => x + y

  def func11(args: Int*) = for (arg <- args) println(arg)

  def func12(x: Int): Int = {
    if (x == 0) {
      throw new Exception("The Exception")
    } else {
      func12(x - 1)
    }
  }

  def hello1(m: Int): Int = m

  def hello2(m: Int, n: Int) = m * n

  def main(args: Array[String]) {
    println(func1(78))
    println(func2(hello1)(2))
    println(func3(hello2)(2, 3))
    println(func4(3))
    println(func4(1))
    println(func6(7, 2))
    println(func8(1, 89, 3))
    println(func9.apply(8))
    println(func10(12)(2))
    func11(List(1, 2, 3): _*)
  }

}
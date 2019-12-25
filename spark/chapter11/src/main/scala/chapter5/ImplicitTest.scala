package chapter5


object ImplicitTest {

  implicit def a2RichA(a: A) = new RichA(a)

  def main(args: Array[String]): Unit = {
    val a = new A
    a.rich
  }

}

class Implicit {}

class A {}

class RichA(a: A) {

  def rich {
    println("Hello Scala implicit!!!")
  }

}
package chapter1

object TupleTest {

  def main(args: Array[String]): Unit = {
    val tuple1 = (1, 2, 3, 4, "hello")
    println(tuple1._1)
    println(tuple1._2)
  }

}

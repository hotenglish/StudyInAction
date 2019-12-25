package chapter1

object Conditions {

  def main(args: Array[String]): Unit = {
    val i = if (3 > 1) 3 else 1
    var x = 10
    if (x < 20) {
      println("This is if test")
    }
    val result1 = if (x == 10) {
      10
    }
    println(result1)
    val result2 = if (x > 5) x else 5
    println(result2)
  }

}

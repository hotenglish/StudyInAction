package chapter4

object ExtractorTest2 {

  def main(args: Array[String]) {
    val x = ExtractorTest2(5)
    println(x)
    x match {
      case ExtractorTest2(num) => println(x + " is bigger two times than " + num)
      case _ => println("i can not calculate")
    }
  }

  def apply(x: Int) = x * 2

  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
}



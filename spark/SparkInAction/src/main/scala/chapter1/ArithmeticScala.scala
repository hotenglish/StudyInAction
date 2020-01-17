package chapter1

object ArithmeticScala {

  def main(args: Array[String]): Unit = {
    println(1 + 2)
    println(5 - 1)
    println(3 * 5)
    println(6 / 3)
    println(5 % 2)
    println(3 == 2)
    println(3 != 2)
    println(3 > 1)
    println(3 < 1)
    println(true && false)
    println(3 > 1 && 2 != 1)
    println(3 > 1 || 2 != 1)
    println(!(3 > 1))

    println("---------------------------------------");

    println(0 & 0)
    println(0 | 0)
    println(0 ^ 0)
    println(0 & 1)
    println(0 | 1)
    println(0 ^ 1)
    println(1 & 1)
    println(1 | 1)
    println(1 ^ 1)

    println("---------------------------------------");

    var a = 3 + 8
    a += 3
    println(a)
    a -= 5
    println(a)
    a *= 2
    println(a)
  }

}

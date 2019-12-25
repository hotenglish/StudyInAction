package chapter1

object LoopTest {

  def main(args: Array[String]) {

    for (i <- 1 to 10) print(i + "")

    println()

    for (i <- 1 until 10) print(i + "")

    println()

    for (i <- 1 to 10 if (i % 2) == 0) print(i + "")

    println()

    for (i <- 1 to 10 if (i % 2) == 0; if i != 2) print(i + "")

    println()

    for (i <- 1 to 5; j <- 10 to 5 if (i % 2) == 0) print(i * j + "")

    val v1 = for (i <- 1 to 5) yield i

    println("v1=" + v1)

    var i: Int = 0

    while (i <= 5) {
      i += 1
      print(i + "")
    }

    println()

    var count = 5
    do {
      println("count value is:" + count)
      count -= 1
    } while (count > 0)

  }

}

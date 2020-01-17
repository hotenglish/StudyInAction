package chapter4

class TestTypeBorder[T <: Comparable[T]](val first: T, val second: T) {

  def bigger = if (first.compareTo(second) < 0) second else first

}

object ExecuteTestTypeBorder {

  def main(args: Array[String]) {
    val t = new TestTypeBorder("first", "second")
    println(t.bigger)
  }

}
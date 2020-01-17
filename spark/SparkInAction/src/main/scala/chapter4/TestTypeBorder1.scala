package chapter4

class TestTypeBorder1[T <% Ordered[T]](val first: T, val second: T) {

  def bigger = if (first.compareTo(second) < 0) second else first

}

object ExecuteTestTypeBorder1 {

  def main(args: Array[String]) {
    var t = new TestTypeBorder1(1, 2)
    println(t.bigger)
  }

}

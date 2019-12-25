package chapter4

class TestBorderView[T <% Comparable[T]](val first: T, val second: T) {

  def bigger = if (first.compareTo(second) < 0) second else first

}

object TestBorderView {

  def main(args: Array[String]) {
    var t = new TestBorderView(1, 2)
    println(t.bigger)
  }

}

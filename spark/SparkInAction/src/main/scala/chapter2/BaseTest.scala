package chapter2

object BaseTest {

  def main(args: Array[String]): Unit = {
    val bt = new BaseTest
    bt.pri
    println(bt.concat(bt))
  }

}

private[chapter2] class BaseTest {

  private[this] val a = "NiHao"

  private[chapter2] val b = "Hi"

  def pri = {

    println(this.a)
  }

  def concat(bt: BaseTest) = {
    bt.b + "Scala"
  }

}

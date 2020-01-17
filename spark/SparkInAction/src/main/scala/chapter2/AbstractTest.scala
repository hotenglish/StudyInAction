package chapter2

abstract class AbstractTest {

  var str: String
  var strImpl: String = "zhangsan"
  var integer: Int
  var bool: Boolean

  def add(a: Int, b: Int): Int

  def muti(x: Int, y: Int): Int = {
    x * y
  }
}


class AbstractTest1 extends AbstractTest {
  override var str: String = _
  override var integer: Int=_
  override var bool: Boolean=_

  override def add(a: Int, b: Int): Int = {
    3 + 5
  }

}

object TestAbstractTest1 {
  def main(args: Array[String]) {
    val abstractTest1 = new AbstractTest1
    println(abstractTest1.bool)
    println(abstractTest1.integer)
    println(abstractTest1.strImpl)
    println(abstractTest1.str)
    println(abstractTest1.add(3, 5))
    println(abstractTest1.muti(3, 4))
  }
}

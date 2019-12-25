package chapter5

object Int2StringTest {

  implicit def int2String(i:Int) = i.toString

  def main(args: Array[String]): Unit = {
    println(3.length)
  }

}

package chapter5

object ArgImplicitTest {

  def main(args: Array[String]): Unit = {
      hiScala
  }

  implicit val name: String = "ZhangSan"

  def hiScala(implicit str: String) {
      println("str is:" + str)
  }

}

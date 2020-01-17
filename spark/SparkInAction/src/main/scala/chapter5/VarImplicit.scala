package chapter5

object VarImplicit {

  implicit val name: String = "lishi"

  def hiScala(implicit str: String): Unit = {
      println("str is:" + str)
  }

  def main(args: Array[String]): Unit = {
      hiScala
  }

}

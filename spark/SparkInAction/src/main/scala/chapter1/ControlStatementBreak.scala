package chapter1

import scala.util.Random
import scala.util.control.Breaks._

object ControlStatementBreak {

  def main(args: Array[String]): Unit = {
    breakable {
      while (true) {
        var r = new Random()
        var i = r.nextInt(10)
        println("i==" + i)
        if (i == 5) {
          break
        }
      }
    }
  }

}

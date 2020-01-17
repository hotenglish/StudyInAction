package chapter1

import java.io.FileNotFoundException
import scala.io.Source

object ReadFileTest {

  def main(args: Array[String]): Unit = {
    try {
      val file = Source.fromFile("/yy.txt");
      val lines = file.getLines()
      for (content <- lines) {
        println(content)
      }
    } catch {
      case ex: FileNotFoundException => println("The file you input not exists " + ex)
      case ex: Exception => println(ex)
    } finally {
      println("release resource!!!");
    }
  }

}

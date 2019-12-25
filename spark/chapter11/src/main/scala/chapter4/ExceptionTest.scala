package chapter4

import java.io.{FileNotFoundException, FileReader, IOException}

object ExceptionTest {

  def main(args: Array[String]) {
    try {
      val f = new FileReader("/home/oracle/learn/test.txt")
    } catch {
      case ex: FileNotFoundException => {
        println("Missing file Exception")
      }
      case ex: IOException => {
        println("IO Exception")
      }
      case _: Throwable => println("Unknown Exception")
    } finally {
      println("Exiting finally...")
    }
  }
}
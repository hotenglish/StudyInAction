package chapter1

import scala.collection.mutable.ArrayBuffer

object ArrayTest {

  def main(args: Array[String]): Unit = {
    val attStr = Array("Scala", "Spark")
    val attInt = new Array[Int](10)

    val arrBuffInt = ArrayBuffer[Int]()

    attStr(0) = "Storm"
    attStr.mkString(",")

    val attStrVar = attStr.toBuffer
    for (i <- 0 until attStr.length) println(attStr(i))
    println("-------------------------------------------------")
    for (elem <- attStr) println(elem)

    arrBuffInt += 1

    arrBuffInt += (2, 3, 4, 5)

    arrBuffInt ++= Array(6, 7, 8)

    println("-------------------------------------------------")
    arrBuffInt.foreach(print)
    println("-------------------------------------------------")
    arrBuffInt.trimEnd(2)
    arrBuffInt.foreach(print)
    println("-------------------------------------------------")
    arrBuffInt.remove(2)
    arrBuffInt.foreach(print)
    println("-------------------------------------------------")
    arrBuffInt.remove(2, 3)
    arrBuffInt.foreach(print)
    println("-------------------------------------------------")

  }

}

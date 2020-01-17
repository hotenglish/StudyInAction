package chapter1

object MapTest {

  def main(args: Array[String]) {
    val bigData = Map("Scala" -> 35, "Hadoop" -> 30, "Spark" -> 50)
    println(bigData("Scala"))
    println(bigData.contains("Hadoop"))
    println(bigData.getOrElse("Spark", 70))
    println(bigData.mkString("{", ",", "}"))
    println(bigData.drop(2))

    val bigDataVar = scala.collection.mutable.Map("Scala" -> 35, "Hadoop" -> 30, "Spark" -> 50)

    bigDataVar("Spark") = 100
    bigDataVar += ("KafKa" -> 69)
    bigDataVar -= ("KafKa")

    for ((k, v) <- bigData) println(k + " " + v)
    for (k <- bigData.keySet) println(k)

    for ((k, v) <- bigDataVar) println(k + " " + v)
    for (k <- bigDataVar.keySet) println(k)
  }

}

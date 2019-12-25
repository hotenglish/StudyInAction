package chapter3

class ReUseFuncValue(val bigData: String => String) {

  def reUseFuncValue(technology: String): Unit = {
    println("It's a time of BigData!")
    bigData(technology)
  }

}

object ReUseFuncValue {

  def main(args: Array[String]) = {
    val reUseFuncValue = new ReUseFuncValue(
      {
        technology =>
          println("new Technology of BigData is:" + technology)
          technology
      })

    val reUseFuncValue2 = new ReUseFuncValue(
      {
        technology =>
          println("and:" + technology)
          technology
      })

    reUseFuncValue.reUseFuncValue("Spark")
    reUseFuncValue.bigData("Hadoop")
    reUseFuncValue2.reUseFuncValue("Flink")

    val totalReuse = { technology: String =>
      println("new Technology of BigData is:" + technology)
      technology
    }

    val reUseFuncValue3 = new ReUseFuncValue(totalReuse)
    val reUseFuncValue4 = new ReUseFuncValue(totalReuse)

    reUseFuncValue3.reUseFuncValue("Spark")
    //reUseFuncValue3.bigData("Hadoop")
    reUseFuncValue4.reUseFuncValue("Flink")
  }

}
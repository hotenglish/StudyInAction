package chapter4

object BigData {

  def bigData1(data: String) {
    data match {
      case "Spark" => println("Wow!!!")
      case "Hadoop" => println("OK")
      case _ => println("Something others");
    }
  }

  def bigData2(data: String) {
    data match {
      case "Spark" => println("Wow!!")
      case _ if data == "Flink" => println("Nice!!!")
      case _ => println("Something others");
    }
  }

  def bigData3(data: String) {
    data match {
      case "Spark" => println("Wow!!")
      case data1 if data1 == "Flink" => println(data)
      case _ => println("Something others");
    }
  }

  def main(args: Array[String]) {
    bigData1("Hadoop")
    bigData2("Flink")
    bigData1("upi")
  }

}

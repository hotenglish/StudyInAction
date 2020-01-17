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
      case data1 if data1 == "Flink" => println(data1)
      case _ => println("Something others");
    }
  }

  def main(args: Array[String]) {
    System.out.println("-------------data1----------------")
    bigData1("Spark")
    bigData1("Flink")
    bigData1("Hadoop")

    System.out.println("-------------data2----------------")
    bigData2("Spark")
    bigData2("Flink")
    bigData2("Hadoop")

    System.out.println("-------------data3----------------")
    bigData3("Spark")
    bigData3("Flink")
    bigData3("Hadoop")
  }

}

package chapter14

import org.apache.spark.{SparkConf, SparkContext}

object TopNGroup {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ScalaTopNGroup").setMaster("local");
    val sc = new SparkContext(conf)
    val lines = sc.textFile("src/TopNGroup.txt")
    val top5 = lines.map { line =>
      val splitedLine = line.split(" ")
      (splitedLine(0), splitedLine(1).toInt)
    }
      .groupByKey().map { groupedData =>
      val groupedKey = groupedData._1
      val top5: List[Int] = groupedData._2.toList.sortWith(_ > _).take(5)
      Tuple2(groupedKey, top5)
    }

    top5.foreach { topped =>
      System.out.println("Group key:" + topped._1)
      val toppedValue: Iterator[Int] = topped._2.iterator
      while (toppedValue.hasNext) {
        val value: Integer = toppedValue.next()
        println(value)
      }
      println("************************************")
    }
    sc.stop()
  }

}

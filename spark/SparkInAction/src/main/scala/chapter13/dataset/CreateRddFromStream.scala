package chapter13.dataset

import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object CreateRddFromStream {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("RDD from Stream")

    val ssc = new StreamingContext(conf, Seconds(1))

    val lines = ssc.socketTextStream("localhost", 9999)

    val words=lines.flatMap(_.split(" "))

  }

}

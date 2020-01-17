package chapter13.rddinaction

import org.apache.spark.{SparkConf, SparkContext}

object TakeSampleExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array("A", "B", "C", "D"))

    val takeSampleRDD = rdd1.takeSample(true, 3, 3)

    takeSampleRDD.foreach(e => println(e+" "))

  }

}

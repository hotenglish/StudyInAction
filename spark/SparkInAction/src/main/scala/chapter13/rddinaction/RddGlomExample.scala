package chapter13.rddinaction

import org.apache.spark.{SparkConf, SparkContext}

object RddGlomExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(1 to 10)

    rdd.glom().collect().foreach(e => println(e.mkString(",")))

    sc.stop()

  }

}

package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddUnionExample {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array("A1", "A2", "A3", "A4", "A5"))

    val rdd2 = sc.parallelize(Array("B1", "B2", "B3", "B4", "B5"))

    val unionRDD = rdd1.union(rdd2)

    println(unionRDD.collect().foreach(e => println(e)));

    sc.stop()

  }

}

package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object GroupByExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array("V1", "V2", "U1", "W2", "U2", "V2", "W1"))

    val groupByRdd = rdd1.groupBy(_.substring(0, 1))

    println(groupByRdd.collect().foreach(e => println(e)))

  }

}

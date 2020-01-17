package chapter13

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object RddCogroupExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array(("V1", 1), ("V2", 2), ("V2", 2), ("U1", 2), ("U2", 1), ("U5", 4)))

    val rdd2 = sc.parallelize(Array(("V1", 1), ("V8", 2), ("U1", 2), ("U5", 1)))

    val cogroupRdd = rdd1.cogroup(rdd2)

    println(cogroupRdd.foreach({ e => println(e) }))

  }

}

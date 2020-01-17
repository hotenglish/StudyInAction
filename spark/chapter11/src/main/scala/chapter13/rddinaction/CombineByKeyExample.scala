package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object CombineByKeyExample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf

    conf.setAppName("My First Spark App")

    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(("V1", 1), ("V1", 2), ("V2", 2), ("U3", 1), ("U1", 1), ("U2", 2), ("V2", 2), ("U3", 1)))

    val combineByKeyRdd = rdd.combineByKey((v: Int) => List(v), (c: List[Int], v: Int) => v :: c, (c1: List[Int], c2: List[Int]) => c1 ::: c2)

    println(combineByKeyRdd.collect().foreach(e => println(e)))

  }

}

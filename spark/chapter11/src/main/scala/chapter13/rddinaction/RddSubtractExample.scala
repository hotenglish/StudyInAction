package chapter13

import org.apache.spark.{SparkConf, SparkContext}

object RddSubtractExample {


    def main(args: Array[String]): Unit = {

      val conf = new SparkConf

      conf.setAppName("My First Spark App")

      conf.setMaster("local")

      val sc = new SparkContext(conf)

      val rdd1 = sc.parallelize(Array("A", "B", "C", "D"))

      val rdd2 = sc.parallelize(Array("C", "D", "E", "F"))

      val subtractRDD = rdd1.subtract(rdd2)

      println(subtractRDD.collect().foreach(e => println(e)));

      sc.stop()

    }

}

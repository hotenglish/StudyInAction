package chapter13.dataset

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.mapreduce.TableInputFormat

object CreateRddFromOthers {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("RDD from Stream")

    val sc = new SparkContext(conf)

    val hconf = HBaseConfiguration.create()

    val tableName = "DT_STUDENT"

    hconf.set(TableInputFormat.INPUT_TABLE, tableName)

    val admin = new HBaseAdmin(hconf)

    val hBaseRDD = sc.newAPIHadoopRDD(hconf, classOf[TableInputFormat],
      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
      classOf[org.apache.hadoop.hbase.client.Result])

    hBaseRDD.count()

    sc.stop()

    admin.close()

  }

}

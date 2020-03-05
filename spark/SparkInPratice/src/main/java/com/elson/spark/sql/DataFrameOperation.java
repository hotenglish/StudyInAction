package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.Dataset;

public class DataFrameOperation {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("DataFrameOperation");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        Dataset<Row> df = sqlContext.read().json("file:///home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/students.json");

        df.show();

        df.printSchema();

        df.select("name").show();

        df.select(df.col("name"), df.col("score").plus(1)).show();

        df.filter(df.col("score").gt(80)).show();

        df.groupBy("score").count().show();

        sc.close();

    }

}

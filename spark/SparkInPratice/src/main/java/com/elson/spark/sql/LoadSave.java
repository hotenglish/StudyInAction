package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.Dataset;

public class LoadSave {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("LoadSave");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        Dataset<Row> userDS = sqlContext.read().load("src/main/resources/users.parquet");

        userDS.printSchema();

        userDS.show();

        userDS.select("name", "score").write().save("src/main/resources/backup.parquet");

        sc.close();
    }
}

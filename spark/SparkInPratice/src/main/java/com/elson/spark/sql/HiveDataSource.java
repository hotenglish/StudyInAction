package com.elson.spark.sql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class HiveDataSource {

    public static void main(String args[]) {

        SparkSession sparkSession = SparkSession.builder().master("local").appName("HiveDataSource")
                .config("spark.sql.warehouse.dir", "/home/oracle/Servers/apache-hive-2.3.6-bin/warehouse/")
                .enableHiveSupport().getOrCreate();

        sparkSession.sql("CREATE DATABASE IF NOT EXISTS mytestdb");

        sparkSession.sql("DROP TABLE IF EXISTS student_infos");

        sparkSession.sql("CREATE TABLE IF NOT EXISTS student_infos (name STRING, age INT) " +
                "row format delimited fields terminated by ' ' lines terminated by '\n'");

        sparkSession.sql("LOAD DATA LOCAL INPATH " +
                "'src/main/resources/student_infos.txt' " +
                "INTO TABLE student_infos");

        sparkSession.sql("DROP TABLE IF EXISTS student_scores");

        sparkSession.sql("CREATE TABLE IF NOT EXISTS student_scores (name STRING, score INT) "+
                "row format delimited fields terminated by ' ' lines terminated by '\n'");

        sparkSession.sql("LOAD DATA LOCAL INPATH " +
                "'src/main/resources/student_score.txt' " +
                "INTO TABLE student_scores");

        Dataset<Row> goodStudentDS = sparkSession.sql("select si.name, si.age, ss.score" +
                " from student_infos si join student_scores ss on si.name=ss.name where ss.score >= 80");

        sparkSession.sql("DROP TABLE IF EXISTS good_student_infos");

        goodStudentDS.write().saveAsTable("good_student_infos");

        Dataset<Row> results = sparkSession.table("good_student_infos");

        results.show();

        sparkSession.stop();
    }

}

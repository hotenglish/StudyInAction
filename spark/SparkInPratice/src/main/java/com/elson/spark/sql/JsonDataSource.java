package com.elson.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class JsonDataSource {

    public static void main(String args[]) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("JsonDataSource");

        JavaSparkContext sc = new JavaSparkContext(conf);

        SQLContext sqlContext = new SQLContext(sc);

        Dataset<Row> studentDs = sqlContext.read().json("file:///home/oracle/learn/spark/SparkInPratice/02WorkCountJava/src/main/resources/students.json");

        studentDs.registerTempTable("student_scores");

        Dataset<Row> goodStudentNamesDS = sqlContext.sql("select name, score from student_scores where score >= 80");

        JavaRDD<Row> goodStudentNamesRDD = goodStudentNamesDS.toJavaRDD();

        List<String> goodStudentNames = goodStudentNamesRDD.map(row -> row.getString(0)).collect();

        List<String> studentInfoJSONs = new ArrayList<String>();
        studentInfoJSONs.add("{ \"name\": \"yasaka\", \"age\": 18 }");
        studentInfoJSONs.add("{ \"name\": \"xuruyun\", \"age\": 17 }");
        studentInfoJSONs.add("{ \"name\": \"liangyongqi\", \"age\": 19 }");

        JavaRDD<String> studentInfoRDD = sc.parallelize(studentInfoJSONs);

        Dataset<Row> studentInfoDF = sqlContext.read().json(studentInfoRDD);

        studentInfoDF.registerTempTable("student_infos");

        String sql = "select name, age from student_infos where name in (";

        int length = goodStudentNames.size();

        for (int i = 0; i < length; i++) {
            sql += "'" + goodStudentNames.get(i) + "'";
            if (i < length - 1) {
                sql += ",";
            }
        }
        sql += ")";

        System.out.println(sql);

        Dataset<Row> goodStudentInfo = sqlContext.sql(sql);

        JavaPairRDD<String, Tuple2<Integer, Integer>> goodStudentsRDD = goodStudentInfo.toJavaRDD()
                .mapToPair(row -> new Tuple2<>(String.valueOf(row.get(0)),
                        Integer.valueOf(String.valueOf(row.get(1)))))
                .join(goodStudentNamesRDD.mapToPair(row -> new Tuple2<>(String.valueOf(row.get(0)),
                        Integer.valueOf(String.valueOf(row.get(1))))));

        JavaRDD<Row> goodStudentsRowRdd
                = goodStudentsRDD.map(tuple2 -> RowFactory.create(tuple2._1, tuple2._2._1, tuple2._2._2));

        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        fields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));
        fields.add(DataTypes.createStructField("score", DataTypes.IntegerType, true));

        StructType schema = DataTypes.createStructType(fields);

        Dataset<Row> goodStudentDS = sqlContext.createDataFrame(goodStudentsRowRdd, schema);

        goodStudentDS.write().format("json").mode(SaveMode.Overwrite).save("goodStudentJson");

    }

}
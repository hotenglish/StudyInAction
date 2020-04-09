package com.elson.spark.sql;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDataSource {

    public static void main(String args[]) {

        SparkSession sparkSession = SparkSession.builder().master("local").appName("JsonDataSource")
                .enableHiveSupport().getOrCreate();

        Map<String, String> options = new HashMap<>();

        options.put("url", "jdbc:mysql://node1:3306/SparkJdbc");
        options.put("dbtable", "t_student");
        options.put("user", "root");
        options.put("password", "123456");

        Dataset<Row> studentInfoDS = sparkSession.read().format("jdbc").options(options).load();

        options.put("dbtable", "t_score");

        Dataset<Row> studentScoreDS = sparkSession.read().format("jdbc").options(options).load();

        JavaPairRDD<String, Tuple2<Integer, Integer>> studentsRDD = studentInfoDS.toJavaRDD()
                .mapToPair(row -> new Tuple2<>(String.valueOf(row.get(0)),
                        Integer.valueOf(String.valueOf(row.get(1)))))
                .join(studentScoreDS.toJavaRDD().mapToPair(row -> new Tuple2<>(String.valueOf(row.get(0)),
                        Integer.valueOf(String.valueOf(row.get(1))))));

        JavaRDD<Row> studentsRowRDD
                = studentsRDD.map(tuple2 -> RowFactory.create(tuple2._1, tuple2._2._1, tuple2._2._2))
                .filter(row -> {
                    if (row.getInt(2) > 80) {
                        return true;
                    }
                    return false;
                });

        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        fields.add(DataTypes.createStructField("age", DataTypes.IntegerType, true));
        fields.add(DataTypes.createStructField("score", DataTypes.IntegerType, true));

        StructType schema = DataTypes.createStructType(fields);

        Dataset<Row> goodStudentDS = sparkSession.createDataFrame(studentsRowRDD, schema);

        Row[] rows = (Row[]) goodStudentDS.collect();

        for (int i = 0; i < rows.length; i++) {
            System.out.println(rows[i]);
        }

        goodStudentDS.javaRDD().foreach(row -> {

            String sql = "insert into good_student_infos values(" + "'" + row.getString(0) + "',"
                    + Integer.valueOf(String.valueOf(row.get(1))) + "," + Integer.valueOf(String.valueOf(row.get(2)))
                    + ")";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            Statement stat = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://node1:3306/SparkJdbc", "root", "123456");
                stat = conn.createStatement();
                stat.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }

        });

        sparkSession.close();

    }

}

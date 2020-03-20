package com.elson.hadoop.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveText {

    public static void main(String args[]) throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection conn = DriverManager.getConnection("jdbc:hive2://node1:10000",
                "root", "123456");

        try {
            Statement st = conn.createStatement();
            ResultSet ret = st.executeQuery("select count(*) from t_emp");
            if (ret.next()) {
                System.out.println(ret.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

}

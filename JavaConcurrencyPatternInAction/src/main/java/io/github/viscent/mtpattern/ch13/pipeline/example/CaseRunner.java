package io.github.viscent.mtpattern.ch13.pipeline.example;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 运行以下类可以生成该程序所需的数据库记录：
 * io.github.viscent.mtpattern.ch13.pipeline.example.RecordGenerator
 * 
 * @author Viscent Huang
 *
 */
public class CaseRunner {

	public static void main(String[] args) {

		// 运行本程序前，请根据实际情况修改以下方法中有关数据库连接和FTP账户的信息
		DataSyncTask dst = new DataSyncTask() {

			@Override
			protected Connection getConnection() throws Exception {
				Connection dbConn = null;

				Class.forName("org.hsqldb.jdbc.JDBCDriver");

				dbConn = DriverManager.getConnection(
				    "jdbc:hsqldb:hsql://127.0.0.1/", "SA", "");
				return dbConn;
			}

			@Override
			protected String[][] retrieveFTPServConf() {
				String[][] ftpServerConfigs = new String[][] { { "127.0.0.1",
				    "ftpUser", "sin90=1x" }
				// ,
				// { "192.168.1.104", "datacenter", "abc123" }
				// ,
				// { "192.168.1.103", "datacenter", "abc123" }
				};
				
				return ftpServerConfigs;
			}

		};
		
		dst.run();
	}

}

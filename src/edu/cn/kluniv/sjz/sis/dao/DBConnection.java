package edu.cn.kluniv.sjz.sis.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DBConnection {
	private String USER;
	private String PWD;
	private String SERVERNAME;
	private String PORT;
	private String DBNAME;
	private Connection conn;

	public DBConnection() {
		InputStream iStream = DBConnection.class.getResourceAsStream("jdbc.properties");
		Properties p = new Properties();
		try {
			p.load(iStream);
			USER = p.getProperty("user");
			PWD = p.getProperty("passwrd");
			SERVERNAME = p.getProperty("server");
			PORT = p.getProperty("port");
			DBNAME = p.getProperty("dbName");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void connect() {
		try {
			SQLServerDataSource ds = new SQLServerDataSource();
			ds.setUser("sa");
			ds.setPassword("123456");
			ds.setServerName("localhost");
			ds.setPortNumber(Integer.parseInt("1433"));
			ds.setDatabaseName("SIMDB");
			ds.setTrustServerCertificate(true);

			conn = ds.getConnection();
			System.out.println("Á¬½Óµ½ms server!");

		} catch (SQLException e) {
			System.out.println("database conect failed!");
			e.printStackTrace();
		}
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("database close success!");
			} catch (SQLException e) {
				System.out.println("database close failed!");
				e.printStackTrace();
			}
		}
	}
}

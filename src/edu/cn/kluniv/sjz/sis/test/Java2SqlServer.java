package edu.cn.kluniv.sjz.sis.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Java2SqlServer {
	public static void main(String[] args) {
		// Create datasource.
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("sa");
		ds.setPassword("123456");
		ds.setServerName("localhost");
		ds.setPortNumber(Integer.parseInt("1433"));
		ds.setDatabaseName("SIMDB");
		ds.setTrustServerCertificate(true);
		// trustServerCertificate=true;

		try (Connection con = ds.getConnection()) {
			System.out.println("Á¬½Óµ½ms server!");

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

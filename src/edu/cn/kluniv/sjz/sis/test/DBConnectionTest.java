package edu.cn.kluniv.sjz.sis.test;

import edu.cn.kluniv.sjz.sis.dao.DBConnection;

public class DBConnectionTest {
	public static void main(String[] args) {
		DBConnection dbc = new DBConnection();
		dbc.connect();
		dbc.close();
	}
}

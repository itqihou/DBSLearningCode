package edu.cn.kluniv.sjz.sis.test;

import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.dao.StudentDAO;
import edu.cn.kluniv.sjz.sis.view.StudentView;


public class StudentViewTest {
	public static void main(String[] args) {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		StudentDAO stuDAO=new StudentDAO(dbc);
		stuDAO.selectByNo("2020101");
		new StudentView();
		
		dbc.close();
	}
}

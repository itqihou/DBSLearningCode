package edu.cn.kluniv.sjz.sis.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.dao.CourseDAO;
import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.model.Course;


public class CourseDAOTest {
	public static void selectByNo() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		CourseDAO courseDAO=new CourseDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=courseDAO.selectByNo("1");
		System.out.printf("课程号  |  课程名 | 教师 |  先修课  | 学分\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getString(4)+"  ");
				System.out.println(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}
	
	public static void selectAll()  {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		CourseDAO courseDAO=new CourseDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=courseDAO.selectAll(1);
		System.out.printf("课程号  |  课程名 | 教师 |  先修课  | 学分\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getString(4)+"  ");
				System.out.println(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dbc!=null) {
				dbc.close();
			}
		}		
	}
	
	public static void insert() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		CourseDAO courseDAO=new CourseDAO(dbc);
		Course course=new Course("KL008", "汉语言文学", "2007001", "1", 2);
		courseDAO.insert(course);
		dbc.close();
	}
	
	public static void update() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		String no="KL008";
		CourseDAO courseDAO=new CourseDAO(dbc);
		Course course=new Course("KL008", "计算机组成原理", "2007001", "1", 2);
		courseDAO.update(course,no);
		dbc.close();
	}
	
	public static void delete() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		String no="KL008";
		CourseDAO courseDAO=new CourseDAO(dbc);
		courseDAO.delete(no);
		dbc.close();
	}
	
	public static void main(String[] args) {
//		selectAll();// 通过
		selectByNo();// 通过
//		insert();// 通过
//		delete();// 通过
//		update();
	}
}

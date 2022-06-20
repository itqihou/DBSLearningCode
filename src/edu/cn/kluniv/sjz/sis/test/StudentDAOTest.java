package edu.cn.kluniv.sjz.sis.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.dao.StudentDAO;
import edu.cn.kluniv.sjz.sis.model.Student;

public class StudentDAOTest {

	public static void selectByNo() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		StudentDAO stuDAO=new StudentDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=stuDAO.selectByNo("");
		System.out.printf("学号  |  姓名 | 性别  | 年龄 | 系别\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getInt(4)+"  ");
				System.out.println(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}
	
	public static void selectAll() throws SQLException {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		StudentDAO stuDAO=new StudentDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=stuDAO.selectAll(1);
		System.out.printf("学号  |  姓名 | 性别  | 年龄 | 系别\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getInt(4)+"  ");
				System.out.println(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs!=null) {
				rs.close();
			}
			if (dbc!=null) {
				dbc.close();
			}
		}		
	}
	
	public static void insert() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		StudentDAO stuDAO=new StudentDAO(dbc);
		Student student=new Student("2020001", "纪晓岚", "男", 24, "光电");
		stuDAO.insert(student);
		dbc.close();
	}
	
	public static void update() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		String no="2020001";
		StudentDAO stuDAO=new StudentDAO(dbc);
		Student student=new Student("2020001", "纪晓岚", "男", 24, "物联网");
		stuDAO.update(student,no);
		dbc.close();
	}
	
	public static void delete() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		String no="2020001";
		StudentDAO stuDAO=new StudentDAO(dbc);
		stuDAO.delete(no);
		dbc.close();
	}
	
	public static void selectByDept() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		StudentDAO stuDAO=new StudentDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=stuDAO.selectByDept("");
		System.out.printf("学号  |  姓名 | 性别  | 年龄 | 系别\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getInt(4)+"  ");
				System.out.println(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}
	
	public static void selectBySex() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		StudentDAO stuDAO=new StudentDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=stuDAO.selectBySex("");
		System.out.printf("学号  |  姓名 | 性别  | 年龄 | 系别\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.print(rs.getString(3)+"  ");
				System.out.print(rs.getInt(4)+"  ");
				System.out.println(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

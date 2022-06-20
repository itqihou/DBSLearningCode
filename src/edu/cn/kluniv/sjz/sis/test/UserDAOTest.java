package edu.cn.kluniv.sjz.sis.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.dao.UserDAO;
import edu.cn.kluniv.sjz.sis.model.User;

public class UserDAOTest {
	public static final int userRoleStudent = 1;
	public static final int userRoleTeacher = 2;
	public static final int userRoleAdmin = 3;

	public static void selectByNo() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=userDAO.selectByNo("superuser");
		System.out.printf("账号  |  密码 | 权限\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}
	
	public static void selectAll() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=userDAO.selectAll(userRoleAdmin);
		System.out.printf("账号  |  密码 | 权限\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}
	
	public static void insert() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
		User user=new User("superuser", "123456", userRoleAdmin);
		userDAO.insert(user);
		
		dbc.close();
	}
	
	public static void update() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
		String no="2020101";
		User user=new User("2020101", "123456", userRoleStudent);
		userDAO.update(user, no);
		dbc.close();
	}
	
	public static void delete() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
		String no="001";
		userDAO.delete(no);
		dbc.close();
	}
	
	public static void selectByAccountAndPwd() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
		String account="superuser";
		String passwd="123456";
		ResultSet rs=userDAO.selectByAccountAndPwd(account, passwd);
		System.out.printf("账号  |  密码 | 权限\n");
		try {
			while (rs.next()) {
				System.out.print(rs.getString(1)+"  ");
				System.out.print(rs.getString(2)+"  ");
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.close();
	}
	
	public static void GenerateUserInfo() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		UserDAO userDAO=new UserDAO(dbc);
		userDAO.generateInfo();
		dbc.close();
	}
	
	public static void main(String[] args) {
//		insert(); // 通过
//		GenerateUserInfo();// 通过
//		selectByNo();// 通过
		selectAll();// 通过
//		selectByAccountAndPwd();// 通过
//		delete();// 通过
//		update();// 通过
	}
}

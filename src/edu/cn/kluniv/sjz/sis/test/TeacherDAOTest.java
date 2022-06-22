package edu.cn.kluniv.sjz.sis.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.dao.TeacherDAO;
import edu.cn.kluniv.sjz.sis.model.Teacher;

public class TeacherDAOTest {
	public static void selectByNo() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		TeacherDAO teaDAO=new TeacherDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=teaDAO.selectByNo("2007001");
		System.out.printf("����  |  ���� | �Ա�  | ���� | ϵ��\n");
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
	
	public static void selectAll()  {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		TeacherDAO teaDAO=new TeacherDAO(dbc);
//		User user=new User(null, null, userRoleAdmin);
		ResultSet rs=teaDAO.selectAll(1);
		System.out.printf("����  |  ���� | �Ա�  | ���� | ϵ��\n");
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
		TeacherDAO teaDAO=new TeacherDAO(dbc);
		Teacher tea=new Teacher("2010011", "�ܲ�", "��", 24, "������");
		teaDAO.insert(tea);
		dbc.close();
	}
	
	public static void update() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		String no="2010011";
		TeacherDAO teaDAO=new TeacherDAO(dbc);
		Teacher tea=new Teacher("2010011", "ˮ������", "��", 26, "������");
		teaDAO.update(tea,no);
		dbc.close();
	}
	
	public static void delete() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		String no="2020001";
		TeacherDAO teaDAO=new TeacherDAO(dbc);
		teaDAO.delete(no);
		dbc.close();
	}
	
	public static void selectByDept() {
		DBConnection dbc=new DBConnection();
		dbc.connect();
		TeacherDAO teaDAO=new TeacherDAO(dbc);
		ResultSet rs=teaDAO.selectByDept("������");
		System.out.printf("����  |  ���� | �Ա�  | ���� | ϵ��\n");
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
		TeacherDAO teaDAO=new TeacherDAO(dbc);
		ResultSet rs=teaDAO.selectBySex("��");
		System.out.printf("����  |  ���� | �Ա�  | ���� | ϵ��\n");
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
//		selectByNo();// ͨ��
//		selectAll();// ͨ��
//		selectBySex();// ͨ��
//		selectByDept();// ͨ��
	}

}

package edu.cn.kluniv.sjz.sis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.model.Teacher;
import edu.cn.kluniv.sjz.sis.model.User;

public class TeacherDAO implements BaseDAO<Teacher> {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	DBConnection dbc;
	private String sqlInsert = "INSERT INTO [SJZ].Teacher VALUES(?,?,?,?,?)";
	private String sqlDelete = "DELETE FROM [SJZ].Teacher WHERE tno=?";
	private String sqlUpdate = "UPDATE [SJZ].Teacher SET tname=?,tsex=?,tage=?,tdept=? WHERE tno=?";
	private String sqlSelectByNo = "SELECT * FROM [SJZ].Teacher WHERE tno=?";
	private String sqlSelectAll = "SELECT * FROM [SJZ].Teacher";
	private String sqlSelectBySex = "SELECT * FROM [SJZ].Teacher WHERE tsex=?";
	private String sqlSelectByDept = "SELECT * FROM [SJZ].Teacher WHERE tdept=?";
	
	
	
	public TeacherDAO(DBConnection dbc) {
		this.dbc = dbc;
		conn=dbc.getConnection();
	}

	@Override
	public void insert(Teacher teaEntity) {
		try {
			String account=teaEntity.getTno();
			String passwd=teaEntity.getTno();
			
			User user=new User(account, passwd, userRoleTeacher);
			UserDAO userDAO=new UserDAO(dbc);
			userDAO.insert(user);
			
			ps=conn.prepareStatement(sqlInsert, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, teaEntity.getTno());
			ps.setObject(2, teaEntity.getTname());
			ps.setObject(3, teaEntity.getTsex());
			ps.setObject(4, teaEntity.getTage());
			ps.setObject(5, teaEntity.getTdept());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("TeacherDAO.insert failed!!");
		}
	}

	@Override
	public void delete(String tno) {
		try {
			ps=conn.prepareStatement(sqlDelete, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, tno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("TeacherDAO.delete data failed!");
		}
	}

	@Override
	public void update(Teacher teaEntity, String tno) {
		try {
			ps=conn.prepareStatement(sqlUpdate, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, teaEntity.getTname());
			ps.setObject(2, teaEntity.getTsex());
			ps.setObject(3, teaEntity.getTage());
			ps.setObject(4, teaEntity.getTdept());
			ps.setObject(5, tno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("TeacherDAO.update data failed!");
		}
	}

	@Override
	public ResultSet selectByNo(String tno) {
		try {
			ps=conn.prepareStatement(sqlSelectByNo, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, tno);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("TeacherDAO.select data failed!");
		}
		return null;
	}

	@Override
	public ResultSet selectAll(int role) {
		try {
			if (role==BaseDAO.userRoleAdmin) {
				ps=conn.prepareStatement(sqlSelectAll, 
						ResultSet.TYPE_SCROLL_SENSITIVE, 
						ResultSet.CONCUR_UPDATABLE);
			} else if (role==BaseDAO.userRoleStudent||role==BaseDAO.userRoleTeacher) {
				ps=conn.prepareStatement(sqlSelectAll, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
			}
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("TeacherDAO.select All data failed!");
		}
		return null;
	}
	
	public ResultSet selectBySex(String tsex) {
		try {
			ps=conn.prepareStatement(sqlSelectBySex, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, tsex);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("TeacherDAO.select data failed!");
		}
		return null;
	}
	
	public ResultSet selectByDept(String tdept) {
		try {
			ps=conn.prepareStatement(sqlSelectByDept, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, tdept);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("TeacherDAO.select data failed!");
		}
		return null;
	}

}

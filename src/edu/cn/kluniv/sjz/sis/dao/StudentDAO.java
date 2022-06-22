package edu.cn.kluniv.sjz.sis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.model.Student;
import edu.cn.kluniv.sjz.sis.model.User;

public class StudentDAO implements BaseDAO<Student> {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	DBConnection dbc;
	private String sqlInsert = "INSERT INTO [SJZ].Student VALUES(?,?,?,?,?)";
	private String sqlDelete = "DELETE FROM [SJZ].Student WHERE sno=?";
	private String sqlUpdate = "UPDATE [SJZ].Student SET sname=?,ssex=?,sage=?,sdept=? WHERE sno=?";
	private String sqlSelectByNo = "SELECT * FROM [SJZ].Student WHERE sno=?";
	private String sqlSelectAll = "SELECT * FROM [SJZ].Student";
	private String sqlSelectBySex = "SELECT * FROM [SJZ].Student WHERE ssex=?";
	private String sqlSelectByDept = "SELECT * FROM [SJZ].Student WHERE sdept=?";

	public StudentDAO(DBConnection dbc) {
		this.dbc = dbc;
		conn = dbc.getConnection();
	}

	public ResultSet getResultSet() {
		return rs;
	}

	@Override
	public void insert(Student stuEntity) {
		try {
			String account=stuEntity.getSno();
			String passwd=stuEntity.getSno();
			
			User user=new User(account, passwd, userRoleStudent);
			UserDAO userDAO=new UserDAO(dbc);
			userDAO.insert(user);
			
			ps=conn.prepareStatement(sqlInsert, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, stuEntity.getSno());
			ps.setObject(2, stuEntity.getSname());
			ps.setObject(3, stuEntity.getSsex());
			ps.setObject(4, stuEntity.getSage());
			ps.setObject(5, stuEntity.getSdept());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StudentDAO.insert failed!!");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String sno) {
		try {
			ps=conn.prepareStatement(sqlDelete, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, sno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StudentDAO.delete data failed!");
		}
	}

	@Override
	public void update(Student stuEntity, String sno) {
		try {
			ps=conn.prepareStatement(sqlUpdate, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, stuEntity.getSname());
			ps.setObject(2, stuEntity.getSsex());
			ps.setObject(3, stuEntity.getSage());
			ps.setObject(4, stuEntity.getSdept());
			ps.setObject(5, sno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StudentDAO.update data failed!");
		}
	}

	@Override
	public ResultSet selectByNo(String sno) {
		try {
			ps=conn.prepareStatement(sqlSelectByNo, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, sno);
			rs=ps.executeQuery();
			rs.beforeFirst();
			return rs;
		} catch (SQLException e) {
			System.out.println("StudentDAO.select data failed!");
			e.printStackTrace();
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
			System.out.println("StudentDAO.select All data failed!");
		}
		return null;
	}
	
	public ResultSet selectBySex(String ssex) {
		try {
			ps=conn.prepareStatement(sqlSelectBySex, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, ssex);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("StudentDAO.select data failed!");
		}
		return null;
	}
	
	public ResultSet selectByDept(String sdept) {
		try {
			ps=conn.prepareStatement(sqlSelectByDept, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, sdept);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("StudentDAO.select data failed!");
		}
		return null;
	}

}

package edu.cn.kluniv.sjz.sis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.model.Administrator;
import edu.cn.kluniv.sjz.sis.model.User;




public class AdministratorDAO implements BaseDAO<Administrator> {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	DBConnection dbc;
	private String sqlInsert = "INSERT INTO [SJZ].Administrator VALUES(?,?,?,?,?)";
	private String sqlDelete = "DELETE FROM [SJZ].Administrator WHERE ano=?";
	private String sqlUpdate = "UPDATE [SJZ].Administrator SET aname=?,asex=?,aage=?,adept=? WHERE ano=?";
	private String sqlSelectByNo = "SELECT * FROM [SJZ].Administrator WHERE ano=?";
	private String sqlSelectAll = "SELECT * FROM [SJZ].Administrator";
	private String sqlSelectBySex = "SELECT * FROM [SJZ].Administrator WHERE asex=?";
	private String sqlSelectByDept = "SELECT * FROM [SJZ].Administrator WHERE adept=?";
	
	
	
	public AdministratorDAO(DBConnection dbc) {
		this.dbc = dbc;
		conn=dbc.getConnection();
	}
	
	
	@Override
	public void insert(Administrator adminEntity) {
		try {
			String account=adminEntity.getAno();
			String passwd=adminEntity.getAno();
			
			User user=new User(account, passwd, userRoleAdmin);
			UserDAO userDAO=new UserDAO(dbc);
			userDAO.insert(user);
			
			ps=conn.prepareStatement(sqlInsert, 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, adminEntity.getAno());
			ps.setObject(2, adminEntity.getAname());
			ps.setObject(3, adminEntity.getAsex());
			ps.setObject(4, adminEntity.getAage());
			ps.setObject(5, adminEntity.getAdept());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.insert failed!!");
		}
	}
	@Override
	public void delete(String ano) {
		try {
			ps=conn.prepareStatement(sqlDelete, 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, ano);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.delete data failed!");
		}
	}
	@Override
	public void update(Administrator adminEntity, String ano) {
		try {
			ps=conn.prepareStatement(sqlUpdate, 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, adminEntity.getAname());
			ps.setObject(2, adminEntity.getAsex());
			ps.setObject(3, adminEntity.getAage());
			ps.setObject(4, adminEntity.getAdept());
			ps.setObject(5, ano);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.update data failed!");
		}
	}
	@Override
	public ResultSet selectByNo(String ano) {
		try {
			ps=conn.prepareStatement(sqlSelectByNo, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, ano);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.select data failed!");
		}
		return null;
	}
	@Override
	public ResultSet selectAll(int role) {
		try {
			if (role==BaseDAO.userRoleAdmin) {
				ps=conn.prepareStatement(sqlSelectAll, 
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} else if (role==BaseDAO.userRoleStudent||role==BaseDAO.userRoleTeacher) {
				ps=conn.prepareStatement(sqlSelectAll, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.select All data failed!");
		}
		return null;
	}
	
	public ResultSet selectBySex(String asex) {
		try {
			ps=conn.prepareStatement(sqlSelectBySex, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, asex);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.select data failed!");
		}
		return null;
	}
	
	public ResultSet selectByDept(String adept) {
		try {
			ps=conn.prepareStatement(sqlSelectByDept, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, adept);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("AdministratorDAO.select data failed!");
		}
		return null;
	}
}

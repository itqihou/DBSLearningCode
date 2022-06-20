package edu.cn.kluniv.sjz.sis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.model.SC;

public class SCDAO implements BaseDAO<SC> {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	DBConnection dbc;
	private String sqlInsert = "INSERT INTO [SJZ].SC(sno,cno,tname,term) VALUES(?,?,?,?)";
	private String sqlDelete = "DELETE FROM [SJZ].SC WHERE sno=?";
	private String sqlUpdate = "UPDATE [SJZ].Student SET sno=?,cno=?,tname=?,grade=?,term=? WHERE sno=?";
	private String sqlSelectAll = "SELECT * FROM [SJZ].SC";
	private String sqlSelectByNo = "SELECT SC.cno,Course.cname,SC.grade,SC.term "
	  		+ "FROM [SJZ].SC INNER JOIN  [SJZ].Student"
	  		+ "ON [SJZ].SC.sno = [SJZ].Student.sno INNER JOIN [SJZ].Course "
	  		+ "ON [SJZ].SC.cno = [SJZ].Course.cno "
	      + "WHERE [SJZ].SC.sno=?";
	private String sqlSelectByTeaNo ="SELECT DISTINCT SC.cno,SC.sno,Student.sname,SC.grade,SC.term "
	  		+ "FROM [SJZ].Course INNER JOIN [SJZ].SC "
	  		+ "ON SC.cno=Course.cno INNER JOIN [SJZ].Teacher "
	  		+ "ON SC.tname=Teacher.tname INNER JOIN [SJZ].Student "
	  		+ "ON SC.sno=Student.sno "
	        + "WHERE Teacher.tno=?";
	
	
	
	public SCDAO(DBConnection dbc) {
		this.dbc = dbc;
		conn=dbc.getConnection();
	}

	@Override
	public void insert(SC scEntity) {
		try {			
			ps=conn.prepareStatement(sqlInsert, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, scEntity.getSno());
			ps.setObject(2, scEntity.getCno());
			ps.setObject(3, scEntity.getTname());
			ps.setObject(4, scEntity.getTerm());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SCDAO.insert failed!!");
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
			System.out.println("SCDAO.delete data failed!");
		}
	}

	@Override
	public void update(SC scEntity, String sno) {
		try {
			ps=conn.prepareStatement(sqlUpdate, 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, scEntity.getSno());
			ps.setObject(2, scEntity.getCno());
			ps.setObject(3, scEntity.getTname());
			ps.setObject(4, scEntity.getGrade());
			ps.setObject(5, scEntity.getTerm());
			ps.setObject(6, sno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SCDAO.update data failed!");
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
			System.out.println("SCDAO.select data failed!");
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
			System.out.println("SCDAO.select All data failed!");
		}
		return null;
	}
	
	public ResultSet selectByTeaNo(String tno) {
		try {
			ps=conn.prepareStatement(sqlSelectByTeaNo, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, tno);
			rs=ps.executeQuery();
			rs.beforeFirst();
			return rs;
		} catch (SQLException e) {
			System.out.println("SCDAO.select data failed!");
			e.printStackTrace();
		}
		return null;
	}

}

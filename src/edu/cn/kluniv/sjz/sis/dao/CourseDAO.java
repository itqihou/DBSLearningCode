package edu.cn.kluniv.sjz.sis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.model.Course;

public class CourseDAO implements BaseDAO<Course> {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	DBConnection dbc;
	private String sqlInsert = "INSERT INTO [SJZ].Course(cno,cname,tno,ccredit) VALUES(?,?,?,?)";
	private String sqlDelete = "DELETE FROM [SJZ].Course WHERE cno=?";
	private String sqlUpdate = "UPDATE [SJZ].Course SET cno=?,cname=?,tno=?,cpno=?,ccredit=? WHERE cno=?";
	private String sqlSelectByNo = "SELECT * FROM [SJZ].Course WHERE cno=?";
	private String sqlSelectAll = "SELECT * FROM [SJZ].Course";
	private String sqlSelectByTeaNo = "SELECT cno,cname,Teacher.tname,ccredit "
			+ "FROM [SJZ].Course INNER JOIN [SJZ].Teacher " + "ON [SJZ].Course.tno=[SJZ].Teacher.tno "
			+ "WHERE [SJZ].Course.tno=?";

	public CourseDAO(DBConnection dbc) {
		this.dbc = dbc;
		conn = dbc.getConnection();
	}

	@Override
	public void insert(Course courseEntity) {
		try {
			ps = conn.prepareStatement(sqlInsert, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, courseEntity.getCno());
			ps.setObject(2, courseEntity.getCname());
			ps.setObject(3, courseEntity.getTno());
			ps.setObject(4, courseEntity.getCcredit());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CourseDAO.insert data failed!");
		}
	}

	@Override
	public void delete(String cno) {
		try {
			ps = conn.prepareStatement(sqlDelete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, cno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CourseDAO.delete data failed!");
		}
	}

	@Override
	public void update(Course courseEntity, String cno) {
		try {
			ps = conn.prepareStatement(sqlUpdate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, courseEntity.getCno());
			ps.setObject(2, courseEntity.getCname());
			ps.setObject(3, courseEntity.getTno());
			ps.setObject(4, courseEntity.getCpno());
			ps.setObject(5, courseEntity.getCcredit());
			ps.setObject(6, cno);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CourseDAO.update data failed!");
		}
	}

	@Override
	public ResultSet selectByNo(String cno) {
		try {
			ps = conn.prepareStatement(sqlSelectByNo, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, cno);
			rs = ps.executeQuery();
			rs.beforeFirst();
			return rs;
		} catch (SQLException e) {
			System.out.println("CourseDAO.select data failed!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet selectAll(int role) {
		try {
			if (role == BaseDAO.userRoleAdmin) {
				ps = conn.prepareStatement(sqlSelectAll, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} else if (role == BaseDAO.userRoleStudent || role == BaseDAO.userRoleTeacher) {
				ps = conn.prepareStatement(sqlSelectAll, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("CourseDAO.select All data failed!");
		}
		return null;
	}

	public ResultSet SelectByTeaNo(String tno) {
		try {
			ps = conn.prepareStatement(sqlSelectByTeaNo, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, tno);
			rs = ps.executeQuery();
			rs.beforeFirst();
			return rs;
		} catch (SQLException e) {
			System.out.println("CourseDAO.select data failed!");
			e.printStackTrace();
		}
		return null;
	}

}

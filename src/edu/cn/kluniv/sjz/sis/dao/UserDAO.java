package edu.cn.kluniv.sjz.sis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cn.kluniv.sjz.sis.model.User;

public class UserDAO implements BaseDAO<User> {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	private User user;
	private String sqlInsert = "INSERT INTO [SJZ].Users VALUES(?,?,?)";
	private String sqlDelete = "DELETE FROM [SJZ].Users WHERE account=?";
	private String sqlUpdate = "UPDATE [SJZ].Users SET account=?,passwrd=?,userRole=? WHERE account=?";
	private String sqlSelectByAccount = "SELECT * FROM [SJZ].Users WHERE account=?";
	private String sqlSelectAll = "SELECT * FROM [SJZ].Users";
	private String sqlSelectByAccountAndPwd = "SELECT * FROM [SJZ].Users WHERE account=? AND passwrd=?";
	private String sqlUpdatePwd = "UPDATE [SJZ].Users SET passwrd=? WHERE account=?";
	private String sqlGenerateUserInfo = "INSERT INTO [SJZ].Users(account,passwrd,userRole) "
			+"SELECT [SJZ].[Student].sno AS account,(RIGHT([SJZ].[Student].sno,6)) AS passwrd,1 AS userRole "
			+"FROM [SJZ].[Student] "
			+"UNION "
			+"SELECT [SJZ].[Teacher].tno,(RIGHT([SJZ].[Teacher].tno,6)),2 "
			+"FROM [SJZ].[Teacher] "
			+"UNION "
			+"SELECT [SJZ].[Administrator].ano,(RIGHT([SJZ].[Administrator].ano,6)),3 "
			+"FROM [SJZ].[Administrator] ";

	public UserDAO(DBConnection dbc) {
		conn = dbc.getConnection();
	}
	
	public User saveUser(ResultSet rs) {
		try {
			user= new User(rs.getString(1), rs.getString(2),rs.getInt(3));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(User userEntity) {
		try {
			ps=conn.prepareStatement(sqlInsert, 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, userEntity.getAccount());
			ps.setObject(2, userEntity.getPassword());
			ps.setObject(3, userEntity.getRole());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDAO.insert data failed!");
		}
	}

	@Override
	public void delete(String account) {
		try {
			ps=conn.prepareStatement(sqlDelete, 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, account);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDAO.delete data failed!");
		}
	}

	@Override
	public void update(User userEntity, String account) {
		try {
			ps=conn.prepareStatement(sqlUpdate, 
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setObject(1, userEntity.getAccount());
			ps.setObject(2, userEntity.getPassword());
			ps.setObject(3, userEntity.getRole());
			ps.setObject(4, account);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDAO.update data failed!");
		}
	}

	@Override
	public ResultSet selectByNo(String account) {
		try {
			ps=conn.prepareStatement(sqlSelectByAccount, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, account);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("UserDAO.select data failed!");
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
			System.out.println("UserDAO.select All data failed!");
		}
		return null;
	}

	public ResultSet selectByAccountAndPwd(String account,String passwrd) {
		try {
			ps=conn.prepareStatement(sqlSelectByAccountAndPwd, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setObject(1, account);
			ps.setObject(2, passwrd);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("UserDAO.select data failed!");
		}
		return null;
	}
	
	public void generateInfo() {
		try {
			ps=conn.prepareStatement(sqlGenerateUserInfo);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDAO.generateInfo failed!");
		}
	}
	
	public String updatePasswd(User userEntity,String account,String oldPwd) {
		String str="";
		try {
			rs=selectByAccountAndPwd(account, oldPwd);
			if (!rs.next()) {
				str="原密码错误!";
				return str;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ps=conn.prepareStatement(sqlUpdatePwd);
			ps.setObject(1, userEntity.getPassword());
			ps.setObject(2, account);
			ps.executeUpdate();
			str="原密码修改成功!";
		} catch (SQLException e) {
			System.out.println("UserDAO.updatePasswd failed!");
		}
		return str;
	}
	
}

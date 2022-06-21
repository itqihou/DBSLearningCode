package edu.cn.kluniv.sjz.sis.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

	private ResultSet rs;
	private ResultSetMetaData rsmd;

	public ResultSetTableModel(ResultSet rs) {
		this.rs = rs;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:rs.getMetaData failed��");
			e.printStackTrace();
		}
	}

	public ResultSet getResultSet() {
		return rs;
	}

	public String getColumnName(int columnIndex) {
		try {
			return rsmd.getColumnName(columnIndex + 1);
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:rsmd.getColumnName failed!");
		}
		return null;
	}

	@Override
	public int getRowCount() {
		try {
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			return rowCount;
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:rs.getRowCount failed!");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setValueAt(Object objValue, int rowIndex, int columnIndex) {
		try {
			rs.absolute(rowIndex + 1);
			rs.updateObject(columnIndex + 1, objValue);
			rs.updateRow();
			fireTableCellUpdated(rowIndex, columnIndex);
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:rs.setValueAt failed!");
			e.printStackTrace();
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			rs.absolute(rowIndex + 1);
			return rs.getObject(columnIndex + 1);
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:rs.getValueAt failed!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		try {
			if (rs.getConcurrency()==ResultSet.CONCUR_UPDATABLE) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deleteRow(int rowIndex) {
		try {
			rs.absolute(rowIndex+1);
			rs.deleteRow();
			fireTableRowsUpdated(rowIndex, rowIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertRowStudent() {
		int rowInserted=getRowCount()+1;
		try {
			rs.moveToInsertRow();
			rs.updateString("sno", "001");
			rs.updateString("sname", "������");
			rs.updateString("ssex", "��");
			rs.updateInt("sage", 23);
			rs.updateString("sdept", "��Ϣ����");
			rs.insertRow();
			fireTableRowsInserted(rowInserted, rowInserted);
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:insertRowStudent failed!");
			e.printStackTrace();
		}
	}
	
	public void insertRowTeacher() {
		int rowInserted=getRowCount()+1;
		try {
			rs.moveToInsertRow();
			rs.updateString("tno", "001");
			rs.updateString("tname", "������");
			rs.updateString("tsex", "��");
			rs.updateInt("tage", 23);
			rs.updateString("tdept", "��Ϣ����");
			rs.insertRow();
			fireTableRowsInserted(rowInserted, rowInserted);
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:insertRowTeacher failed!");
			e.printStackTrace();
		}
	}
	
	public void insertRowCourse() {
		int rowInserted=getRowCount()+1;
		try {
			rs.moveToInsertRow();
			rs.updateString("cno", "000");
			rs.updateString("cname", "������");
			rs.updateString("tno", "2006010");			
			rs.updateString("cpno", "000");
			rs.updateInt("ccredit", 3);
			rs.insertRow();
			fireTableRowsInserted(rowInserted, rowInserted);
		} catch (SQLException e) {
			System.out.println("ResultSetTableModel:insertRowCourse failed!");
			e.printStackTrace();
		}
	}
}

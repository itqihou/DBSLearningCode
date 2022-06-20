package edu.cn.kluniv.sjz.sis.dao;

import java.sql.ResultSet;

public interface BaseDAO<T> {
	public static final int userRoleStudent = 1;
	public static final int userRoleTeacher = 2;
	public static final int userRoleAdmin = 3;

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	void insert(T entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param no
	 */
	void delete(String no);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 * @param no
	 */
	void update(T entity, String no);

	/**
	 * ��������ѯʵ��
	 * 
	 * @param no
	 * @return
	 */
	ResultSet selectByNo(String no);

	/**
	 * ��ѯ����ʵ��
	 * 
	 * @param role
	 * @return
	 */
	ResultSet selectAll(int role);

}

package edu.cn.kluniv.sjz.sis.dao;

import java.sql.ResultSet;

public interface BaseDAO<T> {
	public static final int userRoleStudent = 1;
	public static final int userRoleTeacher = 2;
	public static final int userRoleAdmin = 3;

	/**
	 * 插入实体
	 * 
	 * @param entity
	 */
	void insert(T entity);

	/**
	 * 删除实体
	 * 
	 * @param no
	 */
	void delete(String no);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 * @param no
	 */
	void update(T entity, String no);

	/**
	 * 按条件查询实体
	 * 
	 * @param no
	 * @return
	 */
	ResultSet selectByNo(String no);

	/**
	 * 查询所有实体
	 * 
	 * @param role
	 * @return
	 */
	ResultSet selectAll(int role);

}

package com.cumt.dao;

import java.util.List;

import com.cumt.model.Permission;

public interface PermissionDAO {
	/**
	 * 按菜单模块查找其功能点
	 * @param menuId
	 * @return
	 */
	List<Permission> findByMnueId(Integer menuId);
	/**
	 * 按perId和menuId查询
	 * @param per
	 * @return
	 */
	Permission findByPer(Integer[] values);
	/**
	 * 按菜单ID和功能号查询
	 * @param menuId
	 * @param i
	 * @return
	 */
	Permission findByMenuIdAndFunction(Integer menuId, int i);

}

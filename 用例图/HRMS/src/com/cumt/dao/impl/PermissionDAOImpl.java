package com.cumt.dao.impl;

import java.util.List;

import com.cumt.dao.PermissionDAO;
import com.cumt.model.Menu;
import com.cumt.model.Permission;

public class PermissionDAOImpl extends BaseDAO implements PermissionDAO{

	@Override
	public List<Permission> findByMnueId(Integer menuId) {
		Menu menu = new Menu();
		menu.setMenuId(menuId);
		return super.findByProperty(Permission.class, "menu", menu);
	}

	@Override
	public Permission findByPer(Integer[] values) {
		String hql = "FROM Permission WHERE perId = ? AND menu.menuId = ?";
		List<Permission> pers = this.findByHQLAndValue(hql, values);
		if(pers.size() > 0){
			return pers.get(0);
		}
		return null;
	}

	@Override
	public Permission findByMenuIdAndFunction(Integer menuId, int i) {
		String hql = "FROM Permission WHERE menu.menuId = ? AND perFunction = ?";
		List<Permission> permission = this.findByHQLAndValue(hql, menuId, i);
		if(permission.size() > 0){
			return permission.get(0);
		}
		return null;
	}

	
}

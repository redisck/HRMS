package com.cumt.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.cumt.bean.MenuBean;
import com.cumt.dao.MenuDAO;
import com.cumt.dao.PermissionDAO;
import com.cumt.dao.RolePermissionDAO;
import com.cumt.dao.impl.BaseDAO;
import com.cumt.model.Menu;
import com.cumt.model.Permission;
import com.cumt.model.RolePermission;
import com.cumt.model.User;
import com.cumt.service.MenuService;

public class MenuServiceImpl implements MenuService{
	private PermissionDAO permissionDAO;
	private MenuDAO menuDAO;
	private RolePermissionDAO rolePermissionDAO;
	
	@Override
	public String getNodeById(String nodeId, User user) {
		int roleId = user.getRole().getRoleId();
		List<Menu> menus = menuDAO.findNoeById(Integer.parseInt(nodeId));
		List<MenuBean> menuBeans = new ArrayList<MenuBean>();
		for(Menu menu: menus){
			if(0 == menu.getMenuLeaf()){
				MenuBean menuBean = new MenuBean();
				menuBean.setId(menu.getMenuId());
				menuBean.setLeaf(menu.getMenuLeaf());
				menuBean.setMenuParentId(menu.getMenuParentId());
				menuBean.setMenuUrl(menu.getMenuUrl());
				menuBean.setText(menu.getMenuTitle());
				menuBean.setMenuIcon(menu.getMenuIcon());
				menuBeans.add(menuBean);
			}else{
				Permission permission = permissionDAO.findByMenuIdAndFunction(menu.getMenuId(), 1); //查询出菜单的查询模板的权限序号
				RolePermission rolePer = null;
				if(null != permission){
					rolePer = rolePermissionDAO.findByPerIdAndRoleId(permission.getPerId(), String.valueOf(roleId));
				}
				//有查询权限的菜单才显示
				if(null != rolePer){
					MenuBean menuBean = new MenuBean();
					menuBean.setId(menu.getMenuId());
					menuBean.setLeaf(menu.getMenuLeaf());
					menuBean.setMenuParentId(menu.getMenuParentId());
					menuBean.setMenuUrl(menu.getMenuUrl());
					menuBean.setText(menu.getMenuTitle());
					menuBean.setMenuIcon(menu.getMenuIcon());
					menuBeans.add(menuBean);
				}
			}
		}
		return JSONArray.fromObject(menuBeans).toString();
	}

	@Override
	public List<Menu> getAllLeaf() {
		return menuDAO.findAllLeaf();
	}

	
	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}

	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	public RolePermissionDAO getRolePermissionDAO() {
		return rolePermissionDAO;
	}

	public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
		this.rolePermissionDAO = rolePermissionDAO;
	}

}

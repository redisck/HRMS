package com.cumt.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.cumt.bean.RoleBean;
import com.cumt.dao.RoleDAO;
import com.cumt.dao.RolePermissionDAO;
import com.cumt.dao.UserDAO;
import com.cumt.enums.StaticValue;
import com.cumt.model.Role;
import com.cumt.service.RoleService;

public class RoleServiceImpl implements RoleService{
	private RoleDAO roleDAO;
	private UserDAO userDAO;
	private RolePermissionDAO rolePerDAO;
	
	@Override
	public String list() {
		List<Role> roles = roleDAO.findAll();
		List<RoleBean> roleBeans = new ArrayList<RoleBean>();
		for(Role role: roles){
			RoleBean roleBean = new RoleBean();
			roleBean.setId(role.getRoleId());
			roleBean.setText(role.getRoleName());
			roleBeans.add(roleBean);
		}
		String roleJson = JSONArray.fromObject(roleBeans).toString();
		return roleJson;
	}	
	
	@Override
	public String getAll() {
		List<Role> roles = roleDAO.findAll();
		return JSONArray.fromObject(roles).toString();
	}
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public String save(Role role) {
		if(roleDAO.save(role)){
			return StaticValue.SAVE_SUCCESS;
		}
		return StaticValue.SAVE_FAILURE;
	}

	@Override
	public String deleteById(String id) {
		userDAO.deleteByRole(Integer.parseInt(id));
		rolePerDAO.deleteByRole(id);
		if(roleDAO.deleteById(id)){
			return StaticValue.DELETE_SUCCESS;
		}
		return StaticValue.DELETE_FAILURE;
	}

	@Override
	public String update(Role role) {
		if(roleDAO.update(role)){
			return StaticValue.UPDATE_SUCCESS;
		}
		return StaticValue.UPDATE_FAILURE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public RolePermissionDAO getRolePerDAO() {
		return rolePerDAO;
	}

	public void setRolePerDAO(RolePermissionDAO rolePerDAO) {
		this.rolePerDAO = rolePerDAO;
	}
}

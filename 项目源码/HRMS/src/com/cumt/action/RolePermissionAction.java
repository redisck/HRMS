package com.cumt.action;
import com.cumt.service.RolePermissionService;
public class RolePermissionAction extends BaseAction{
	
	private static final long serialVersionUID = 6665158167307478793L;
	private RolePermissionService rolePerService;
	private String checkedNodesIds;
	private String menuId;
	
	public void save(){
		String msg = rolePerService.save(this.checkedNodesIds);
		this.out("{success: true, msg: '"+msg+"'}");
	}
	
	public String getCheckedNodesIds() {
		return checkedNodesIds;
	}

	public void setCheckedNodesIds(String checkedNodesIds) {
		this.checkedNodesIds = checkedNodesIds;
	}

	public RolePermissionService getRolePerService() {
		return rolePerService;
	}

	public void setRolePerService(RolePermissionService rolePerService) {
		this.rolePerService = rolePerService;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}

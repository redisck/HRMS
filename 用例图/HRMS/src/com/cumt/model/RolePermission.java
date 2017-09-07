package com.cumt.model;


public class RolePermission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6150822529046050677L;
	private Integer rpId;
	private Role role;
	private Permission permission;
	public RolePermission() {
	}
	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;
	}

	// Property accessors

	public Integer getRpId() {
		return this.rpId;
	}

	public void setRpId(Integer rpId) {
		this.rpId = rpId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
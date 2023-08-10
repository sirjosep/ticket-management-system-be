package com.lawencon.ticketjosep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_role")
public class Role extends Base {
	@Column(name = "role_code", length = 5, unique = true, nullable = false)
	private String roleCode;
	
	@Column(name = "role_name", length = 12, nullable = false)
	private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}

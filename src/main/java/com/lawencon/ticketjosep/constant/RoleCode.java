package com.lawencon.ticketjosep.constant;

public enum RoleCode {
	ADMIN("ADMIN", "Super Admin"), PIC("PIC", "PIC"), DEVELOPER("DEV", "Developer"), CUSTOMER("CUST", "Customer");
	
	public final String roleCode;
	public final String roleName;
	
	RoleCode(String roleCode, String roleName){
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
}

package com.stackroute.keepnote.dto;

public class RoleDto {
	
	private String roleUid;
	private String roleName;
	private String isAccessible;
	private int status;
	private String institutionId;

	
	
	public String getRoleUid() {
		return roleUid;
	}
	public void setRoleUid(String roleUid) {
		this.roleUid = roleUid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getIsAccessible() {
		return isAccessible;
	}
	public void setIsAccessible(String isAccessible) {
		this.isAccessible = isAccessible;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	
	
	

}

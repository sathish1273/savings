package com.stackroute.keepnote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Role implements Serializable {

	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ROLE_SEQ")
	@SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)	
	private long roleUid;
	
	@Column
	private String roleName;
	
	@Column
	private String isAccessible;
	
	@Column
	private int status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	
	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getRoleUid() {
		return roleUid;
	}

	public void setRoleUid(long roleUid) {
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
	
	
	
	
	
}

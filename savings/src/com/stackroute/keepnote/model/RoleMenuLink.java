package com.stackroute.keepnote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ROLE_MENU_LINK")
@org.hibernate.annotations.Proxy(lazy=false)
public class RoleMenuLink implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ROLE_MENU_LINK_ID_SEQ")
	@SequenceGenerator(name = "ROLE_MENU_LINK_ID_SEQ", sequenceName = "ROLE_MENU_LINK_ID_SEQ", allocationSize = 1)
	private Long id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Role role;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private MenuMaster inventoryMenu;
	@Column
	private String accessible_status;
	@Column
	private int displayOrderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	
	
	public String getAccessible_status() {
		return accessible_status;
	}
	public void setAccessible_status(String accessible_status) {
		this.accessible_status = accessible_status;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public void setDisplayOrderId(int displayOrderId) {
		this.displayOrderId = displayOrderId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public MenuMaster getInventoryMenu() {
		return inventoryMenu;
	}
	public void setInventoryMenu(MenuMaster inventoryMenu) {
		this.inventoryMenu = inventoryMenu;
	}
	public String getAccessible() {
		return accessible_status;
	}
	public void setAccessible(String accessible) {
		this.accessible_status = accessible;
	}
	public Integer getDisplayOrderId() {
		return displayOrderId;
	}
	public void setDisplayOrderId(Integer displayOrderId) {
		this.displayOrderId = displayOrderId;
	}

	
}

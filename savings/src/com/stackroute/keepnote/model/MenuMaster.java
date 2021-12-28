package com.stackroute.keepnote.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MENU_MASTER")
@org.hibernate.annotations.Proxy(lazy=false)
public class MenuMaster implements Serializable {

	@Id
	@Column(name="MENU_UID",length=5)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MENU_MASTER_SEQ")
	@SequenceGenerator(name = "MENU_MASTER_SEQ", sequenceName = "MENU_MASTER_SEQ", allocationSize = 1)
	private long id;
	@Column(name="MENU_NAME",length =200)
	private String menuName;
	@Column(name="UI_URL",length =200)
	private String url;
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	private MenuMaster parent;
	@Column(name="STATUS_NAME",length =200)
	private String updateStatus;
	@OneToMany(fetch = FetchType.EAGER,targetEntity=MenuMaster.class, cascade=CascadeType.ALL,
            mappedBy="parent")
	private List<MenuMaster> childrens;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MenuMaster getParent() {
		return parent;
	}
	public void setParent(MenuMaster parent) {
		this.parent = parent;
	}
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public List<MenuMaster> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<MenuMaster> childrens) {
		this.childrens = childrens;
	}

}
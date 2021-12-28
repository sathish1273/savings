package com.stackroute.keepnote.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InventoryMenu  implements Comparable<InventoryMenu>,Serializable{

	
	
	private String menuName;
	
	private String parentId;
	
	private String menu_uid;
	
	private List<InventoryMenu> subMenu =new ArrayList<InventoryMenu>();
	
	private String updateStatus;
	
	private String url;
	
	private Integer orderId;
	
	private String icon;
	

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenu_uid() {
		return menu_uid;
	}

	public void setMenu_uid(String menu_uid) {
		this.menu_uid = menu_uid;
	}

	public String getMenuName() {
		return menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public List<InventoryMenu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<InventoryMenu> subMenu) {
		this.subMenu = subMenu;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public int compareTo(InventoryMenu o) {
		
		return this.orderId-o.getOrderId();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}

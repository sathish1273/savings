package com.stackroute.keepnote.dto;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.keepnote.dto.BusinessMessage;
import com.stackroute.keepnote.dto.ServiceStatus;

public class CommonResponse {
	
	private ServiceStatus serviceStatus;
	List<BusinessMessage> businessMessage = new ArrayList<BusinessMessage>();
	private Object obj;
	private Object successobj;
	private String from;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Object getSuccessobj() {
		return successobj;
	}
	public void setSuccessobj(Object successobj) {
		this.successobj = successobj;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public List<BusinessMessage> getBusinessMessage() {
		return businessMessage;
	}
	public void setBusinessMessage(List<BusinessMessage> businessMessage) {
		this.businessMessage = businessMessage;
	}
	
	

}

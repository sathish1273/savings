package com.stackroute.keepnote.dto;
public class BusinessMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessMessage(String message) 
	{
		this.message = message;
	}
	
	public BusinessMessage() 
	{
	}
}
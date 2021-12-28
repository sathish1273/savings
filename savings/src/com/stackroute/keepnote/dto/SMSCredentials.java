package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum SMSCredentials {
	ACCOUNT_SID("AC0dd6f6b2a04938b60c61734a8f62f30a"),
	AUTH_TOKEN("190aec9cfdbd86ca84f6acaab5ac0866"),
	TWILIO_NUMBER("+12056240673");
	private String value;
	public static Map<String, String> smsCredentials;
	
	private SMSCredentials(String string) {
		 this.value = string;
		 putValue(string,this.name());
		 }
	
	     private static void putValue(String string,String name) {
		 if (smsCredentials == null)
			 smsCredentials = new HashMap<>();
		     smsCredentials.put(string, name);
		 }

	
		 public String getValue() {
		 return value;
		 }
		 
  }

package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum States {
	
	Telangana("India");
	
	private String value;
	public static Map<String, String> integers;
	
	States(String value) {
		 this.value = value;
		 putValue(this.name(),value);
		 }
	
	     private static void putValue(String value,String name) {
		 if (integers == null)
		 integers = new HashMap<>();
		 integers.put(value, name);
		 }

	
		 public String getValue() {
		 return value;
		 }
		 
  }



package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum Districts {
	WarangalRural("Telangana"),
	Mahabubabad("Telangana");

	private String value;
	public static Map<String, String> integers;
	
	Districts(String value) {
		 this.value = value;
		 putValue(value,this.name());
		 }
	
	     private static void putValue(String value,String name) {
		 if (integers == null)
		 integers = new HashMap<>();
		 integers.put(name,value);
		 }

	
		 public String getValue() {
		 return value;
		 }

}

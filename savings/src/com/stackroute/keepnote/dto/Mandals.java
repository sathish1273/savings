package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum Mandals {
	
    Parvathagiri("WarangalRural"),
    Nekkonda("WarangalRural");
	
	private String value;
	public static Map<String, String> integers;
	
	Mandals(String value) {
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

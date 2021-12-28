package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum OccurranceEnum {
	Active(1),
	InActive(2),
	Pending(3);
	
	private int value;
	public static Map<Integer, String> integers;
	
	OccurranceEnum(int value) {
		 this.value = value;
		 putValue(value,this.name());
		 }
	
	     private static void putValue(int value,String name) {
		 if (integers == null)
		 integers = new HashMap<>();
		 integers.put(value, name);
		 }

	
		 public int getValue() {
		 return value;
		 }

}

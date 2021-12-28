package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum LedgerStatusE {
	Open(1),
	Close(2);
	
	private int value;
	public static Map<Integer, String> integers;
	
	LedgerStatusE(int value) {
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

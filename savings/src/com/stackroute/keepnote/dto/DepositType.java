package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum DepositType {
    Initial_Contribution(1),
    Monthly_Share(2),
	Previous_Pending(3);
	private int value;
	public static Map<Integer, String> integers;
	
	private DepositType(int value) {
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

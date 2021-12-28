package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum CalculationTypeE {
		    Monthly_Contributions(1),
		    Quarterly_Contributions(2),
		    HalfYearly_Contributions(3),
		    Yearly_Contributions(4);
		    
			private int value;
			public static Map<Integer, String> integers;
			
			private CalculationTypeE(int value) {
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

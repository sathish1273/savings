package com.stackroute.keepnote.dto;

import java.util.HashMap;
import java.util.Map;

public enum QuestionsEnum {
    What_is_your_mother_maiden_name(1),
    What_is_your_firt_mobileNo(2),
    what_is_your_favorite_game(3),
    what_is_your_birthPlace(4);
    
	private int value;
	public static Map<Integer, String> integers;
	
	private QuestionsEnum(int value) {
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

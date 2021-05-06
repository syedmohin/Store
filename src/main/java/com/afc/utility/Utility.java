package com.afc.utility;

import java.time.format.DateTimeFormatter;

public class Utility {
	private Utility() {}
	public static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static String capitalize(String str) {
		if (str == null || str.isEmpty())
			return str;
	   return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
}

package com.untils;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	public static String getJSON(HttpServletRequest request){
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
		} catch (Exception e) {e.printStackTrace(); }
		return jb.toString();
	}
}

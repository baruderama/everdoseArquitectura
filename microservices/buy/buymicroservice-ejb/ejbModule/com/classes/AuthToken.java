package com.classes;

import java.util.Date;

public class AuthToken {
	private String username;
	private Date expirationTime;
	private String hash;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}

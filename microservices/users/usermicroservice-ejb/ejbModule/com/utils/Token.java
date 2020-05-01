package com.utils;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public class Token {
	
	private static final int SECRET_HASH_KEY=47686128;
	private String username;
	private Date expirationTime;
	private byte[] hash;
	
	public Token(String username, Date expirationTime) {
		this.username=username;
		this.expirationTime=expirationTime;
		byte[] hashedKey=ByteBuffer.allocate(4).putInt(SECRET_HASH_KEY).array();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(hashedKey);
			String hashString=username+expirationTime.toString();
			this.hash = md.digest(hashString.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isValid() {
		byte[] hashedKey=ByteBuffer.allocate(4).putInt(SECRET_HASH_KEY).array();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(hashedKey);
			String hashString=this.username+this.expirationTime.toString();
			byte[] hashTest = md.digest(hashString.getBytes(StandardCharsets.UTF_8));
			if(!Arrays.equals(hashTest,this.hash)) {
				return false;
			}else {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

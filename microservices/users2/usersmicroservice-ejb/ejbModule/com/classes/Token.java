package com.classes;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class Token {
	
	private static final int SECRET_HASH_KEY=47686128;
	private String username;
	private Date expirationTime;
	private String hash;
	
	public Token(String username, Date expirationTime) {
		this.username=username;
		this.expirationTime=expirationTime;
		try {
			String hashString=username+expirationTime.toString();
			this.hash = encrypt(hashString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public boolean isValid() {
		try {
			Calendar date = Calendar.getInstance();
			long t= date.getTimeInMillis();
    		Date now=new Date(t);
			if(this.expirationTime.compareTo(now)<0) {
				return false;
			}
			String hashString=this.username+this.expirationTime.toString();
			String hashTest = encrypt(hashString);
			if(hashTest.equals(this.hash)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String toJson() {
		String json="{";
		json+="\"hash\":\""+this.hash+"\",";
		json+="\"expirationTime\":\""+this.expirationTime.toString()+"\",";
		json+="\"username\":\""+this.username+"\"";
		json+="}";
		return json;
	}
	
	
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = 
	    new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	 private String encrypt(String valueToEnc) throws Exception {
	    Key key = generateKey();
	    Cipher c = Cipher.getInstance(ALGORITHM);
	    c.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encValue = c.doFinal(valueToEnc.getBytes());
	    String encryptedValue = Base64.getEncoder().encodeToString(encValue);
	    return encryptedValue;
	}

	private static Key generateKey() throws Exception {
	    Key key = new SecretKeySpec(keyValue, ALGORITHM);
	    return key;
	}
	
}

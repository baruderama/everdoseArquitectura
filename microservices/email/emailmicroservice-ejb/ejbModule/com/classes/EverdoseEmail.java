package com.classes;

import java.util.Properties;  
import javax.mail.*;  

public class EverdoseEmail {

	public final static String HOST="smtp.gmail.com";
	public final static String EMAIL_FROM="eratoservices@gmail.com";//change accordingly  
	public final static String USERNAME="eratoservices@gmail.com";
	public final static String PASSWORD="eratoerato2019";
	  
	private static Properties props;  
	   
	public static String supplyProductsTemplate = ""
			+ "Buenos días, {{NAME}}"
			+ "\n"
			+ "\n"
			+ "Por favor, envíenos:"
			+ "\n"
			+ "{{PRODUCTS}}"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "Muchas gracias."
			;

	public static Session getSession() {
		props = new Properties();
		props.put("mail.smtp.host", HOST);  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");  
	    props.put("mail.user", USERNAME);
	    props.put("mail.password", PASSWORD );
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    
		Session session = Session.getDefaultInstance(props,  
			    new javax.mail.Authenticator() {  
			      protected PasswordAuthentication getPasswordAuthentication() {  
			    return new PasswordAuthentication(USERNAME,PASSWORD);  
			      }  
			    });  
		return session;
	}
}


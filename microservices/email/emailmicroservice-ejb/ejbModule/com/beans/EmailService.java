package com.beans;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.classes.EverdoseEmail;
import com.classes.Product;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import javax.mail.*;  
import javax.mail.internet.*;  

/**
 * Session Bean implementation class EmailService
 */
@Stateless
@LocalBean
public class EmailService implements EmailServiceRemote, EmailServiceLocal {

    /**
     * Default constructor. 
     */
    public EmailService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean supplyProduct(String emailTo, String supplierName, String productsString ) {
		
		Type type = new TypeToken<List<Product>>(){}.getType();
		List<Product> products = new Gson().fromJson( productsString, type );
		
		String template = EverdoseEmail.supplyProductsTemplate;
		
		String text = template.replace("{{NAME}}", supplierName );
		
		String productsEmailString = "";
		
		for (Product product : products) {
			productsEmailString+="Cantidad: ";
			productsEmailString+=product.getThreshold();
			productsEmailString+="\n";
			productsEmailString+="Nombre: ";
			productsEmailString+=product.getName();
			productsEmailString+="\n";
			productsEmailString+="Descripción: ";
			productsEmailString+=product.getDescription();
			productsEmailString+="\n";
			productsEmailString+="\n";
		}
		
		text = text.replace("{{PRODUCTS}}", productsEmailString );
		
		 try {  
			 
			Session session = EverdoseEmail.getSession();
			Message msg = new MimeMessage(session);
			
            msg.setFrom(new InternetAddress(EverdoseEmail.EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));

            msg.setSubject("Suministro de productos");
			
            msg.setText(text);
			
            msg.setSentDate(new Date());
            Transport.send(msg);
		   
		     } 
		 catch (MessagingException e) {
			 e.printStackTrace();
		 }  
		
		
		return false;
	}

}

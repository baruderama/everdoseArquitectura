package com.beans;

import java.io.BufferedReader;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.Context;

import org.json.JSONObject;

import com.classes.Card;
import com.classes.StripeToken;
import com.google.gson.Gson;
import com.microserviceslocator.ServiceLocator;

/**
 * Session Bean implementation class BuyService
 */
@Stateless
@LocalBean
public class BuyService implements BuyServiceRemote, BuyServiceLocal {

    /**
     * Default constructor. 
     */
    public BuyService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean buy(String data) {
		BufferedReader brConsoleReader = null; 
		Properties props;
		InitialContext ctx;
		   
        Gson g = new Gson(); 
        JSONObject json = new JSONObject(data);
        String stripeToken_str = json.get("stripeToken").toString();
        StripeToken stripeToken = g.fromJson(stripeToken_str, StripeToken.class);
        
        ServiceLocator sloc = new ServiceLocator();
        sloc.getPaymenntsServiceRemote().creditPayment(stripeToken.getId());
        return false;
	}

}

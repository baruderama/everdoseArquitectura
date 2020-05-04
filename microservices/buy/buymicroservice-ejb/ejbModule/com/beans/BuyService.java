package com.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utils.ProductAdapter;
import com.utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;


/**
 * Session Bean implementation class BuyService
 */
@Stateless
@LocalBean
public class BuyService implements BuyServiceRemote, BuyServiceLocal {

	public boolean post(String url, StringEntity entity) throws Exception {
		HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        	int code = response.getStatusLine().getStatusCode();
        	if (code >= 200 && code < 300) {
        		return true;
        	}
        }
        return false;
	}
	
    /**
     * Default constructor. 
     */
    public BuyService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean buy(String data) {
        Gson g = new Gson(); 
        JSONObject json = new JSONObject(data);
        System.out.println("JSON:");
        System.out.println(json.toString());
        String stripeToken_str = json.get("stripeToken").toString();
        String productsList_str = json.get("products").toString();
        String deliveryInformation_str = json.get("delivery_information").toString();
        String financialInformation_str = json.get("financial_information").toString();

        StripeToken stripeToken = g.fromJson(stripeToken_str, StripeToken.class);
        DeliveryInfo deliveryInfo = g.fromJson(deliveryInformation_str, DeliveryInfo.class);
        FinancialInfo financialInfo = g.fromJson(financialInformation_str, FinancialInfo.class);
        
        String destinyAddress = deliveryInfo.getAddress();
        boolean succesfulPayment = false;
        String url = "";
        String toJson = "";
        
//        url = "http://localhost:8080/payments-web-0.0.1-SNAPSHOT/Pay"; 
//        toJson = "{ \"StripeToken\":\""+stripeToken.getId()+"\"}";
//    	try {
//    		StringEntity entity = new StringEntity(toJson);
//			succesfulPayment = post(url, entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    	succesfulPayment = true;
        if (succesfulPayment) {
        	url = "http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/ConsumeProduct"; 
        	toJson = "{ \"destiny_address\":\""+destinyAddress+"\", \"products\":"+productsList_str+"}";
        	System.out.println("Json desde BuyService");
        	System.out.println(toJson);
        	try {
        		StringEntity entity = new StringEntity(toJson);
				post(url, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return false;
	}

}

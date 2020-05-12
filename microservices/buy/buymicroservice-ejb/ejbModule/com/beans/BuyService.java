package com.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.classes.AuthToken;
import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;
import com.google.gson.Gson;

import jdk.jfr.Timestamp;
import model.Car;
import model.ProductAdapter;

/**
 * Session Bean implementation class BuyService
 */
@Stateless
@LocalBean
public class BuyService implements BuyServiceRemote, BuyServiceLocal {

	private final String CHECKTOKEN = "http://localhost:8080/usersmicroservice-web-0.0.1-SNAPSHOT/CheckToken";
	
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
    }

	@Override
	public boolean buy(List<ProductAdapter> products, String token, StripeToken stripeToken, DeliveryInfo deliveryInfo, FinancialInfo financialInfo ) {

        String destinyAddress = deliveryInfo.getAddress();
        boolean succesfulPayment = false;
        boolean succesfulCheck = true;
        String url = "";
        String toJson = "";
        String username="";

        url = CHECKTOKEN;
    	try {
			StringEntity entity = new StringEntity(token);
			succesfulCheck = post(url, entity);
			Gson gson=new Gson();
			AuthToken at=gson.fromJson(token, AuthToken.class);
			username=at.getUsername();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if( !succesfulCheck ) {
    		return false;
    	}
        
    	int total = 0;
    	for (ProductAdapter productAdapter : products) {
    		total += productAdapter.getAmount()*productAdapter.getPrice();
		}
    	
    	System.out.println("Total: "+total);
    	succesfulPayment = true;
    	url = "http://localhost:8080/payments-web-0.0.1-SNAPSHOT/Pay"; 
    	FinancialInfo fi=new FinancialInfo(stripeToken.getId(),total,"description");
    	toJson = new Gson().toJson(fi);
    	try {
    		StringEntity entity = new StringEntity(toJson);
			succesfulPayment = post(url, entity);
		} catch (Exception e) {
			e.printStackTrace();
    		succesfulPayment = false;
		}
        
    	if(succesfulPayment) {
    		Car car =new Car();
    		car.setUsername(username);
    		car.save();
    		for(ProductAdapter pa: products) {
    			pa.setCar(car);
    			pa.save();
    		}
    	}
    	
        if (succesfulPayment) {
        	Gson g = new Gson(); 
        	String productsList_str = g.toJson(products);
        	url = "http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/ConsumeProduct"; 
        	toJson = "{ \"destiny_address\":\""+destinyAddress+"\", \"products\":"+productsList_str+"}";
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

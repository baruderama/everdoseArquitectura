package com.beans;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
	
	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("buymicroservice-jpa");

	private final String CHECKTOKEN = "http://localhost:8080/usersmicroservice-web-0.0.1-SNAPSHOT/CheckToken";
	
	public String post(String url, StringEntity entity) throws Exception {
		HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        	int code = response.getStatusLine().getStatusCode();
        	String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        	System.out.println(responseBody);
        	if (code >= 200 && code < 300) {
        		return responseBody;
        	}
        }
        return null;
	}
	
    /**
     * Default constructor. 
     */
    public BuyService() {
    }

	@Override
	public boolean buy(List<ProductAdapter> products, String token, StripeToken stripeToken, DeliveryInfo deliveryInfo, FinancialInfo financialInfo ,String productsString) {

        String destinyAddress = deliveryInfo.getAddress();
        boolean succesfulPayment = false;
        boolean succesfulCheck = true;
        String url = "";
        String toJson = "";
        String username="";

        url = CHECKTOKEN;
        /*
    	try {
			StringEntity entity = new StringEntity(token);
			succesfulCheck = post(url, entity)!=null;
			Gson gson=new Gson();
			AuthToken at=gson.fromJson(token, AuthToken.class);
			username=at.getUsername();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
    	//if( !succesfulCheck ) {return false; 	}
        
        int total = 0;
        String GetPriceUrl="http://127.0.0.1:8080/stockmicroservice-web-0.0.1-SNAPSHOT/GetPrice";
        try {
        	String jsonString="{\"products\":"+productsString+"}";
        	System.out.println(jsonString);
			StringEntity getpricejson=new StringEntity(jsonString) ;
			total=Integer.valueOf(post(GetPriceUrl,getpricejson));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	System.out.println("Total: "+total);
    	succesfulPayment = true;
    	url = "http://localhost:8080/payments-web-0.0.1-SNAPSHOT/Pay"; 
    	FinancialInfo fi=new FinancialInfo(stripeToken.getId(),total,"description");
    	toJson = new Gson().toJson(fi);
    	try {
    		StringEntity entity = new StringEntity(toJson);
			succesfulPayment = post(url, entity)!=null;
		} catch (Exception e) {
			e.printStackTrace();
    		succesfulPayment = false;
		}
        
    	if(succesfulPayment) {
    		EntityManager em=ENTITY_MANAGER_FACTORY.createEntityManager();
    		Car car =new Car();
    		car.setUsername(username);
    		em.getTransaction().begin();
    		em.persist(car);
    		em.getTransaction().commit();
    		em.flush();
    		System.out.println(car.getId());
    		
    		em.getTransaction().begin();
    		for(ProductAdapter p:products) {
            	System.out.println(p.getID_internal()+"....."+p.getID_internal());
    	    	ProductAdapter productEnt= new ProductAdapter();
    	    	productEnt.setId(p.getId());
    	    	productEnt.setAmount(p.getAmount());
    	    	productEnt.setDescription(p.getDescription());
    	    	productEnt.setImage(p.getImage());
    	    	productEnt.setKeywords(p.getKeywords());
    	    	productEnt.setName(p.getName());
    	    	productEnt.setPrice(p.getPrice());
    	    	productEnt.setType(p.getType());
            	em.persist(productEnt);
            	productEnt.setCar(car);
            }
    		em.getTransaction().commit();
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

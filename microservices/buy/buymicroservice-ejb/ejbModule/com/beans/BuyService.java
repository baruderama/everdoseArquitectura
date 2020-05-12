package com.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;
import com.entities.Cart;
import com.entities.Productadapter;
import com.google.gson.Gson;
import com.utils.ProductAdapter;

/**
 * Session Bean implementation class BuyService
 */
@Stateless
@LocalBean
public class BuyService implements BuyServiceRemote, BuyServiceLocal {
	
	@PersistenceContext(unitName="buymicroservice-jpa",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

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
	public Cart verHistorial(int id) {
		// TODO Auto-generated method stub
		List<Cart> carList=entityManager.createQuery("SELECT c FROM Car c", Cart.class).getResultList();
		//List<Cart> newCarList = null;
		for(Cart c: carList) {
			if(c.getIdUser()==id) {
				return c;
			}
		}
		
		return null;
	}

	@Override
	public boolean buy(List<ProductAdapter> products, String token, StripeToken stripeToken, DeliveryInfo deliveryInfo, FinancialInfo financialInfo,String date,int idUser ) {

        String destinyAddress = deliveryInfo.getAddress();
        boolean succesfulPayment = false;
        boolean succesfulCheck = true;
        String url = "";
        String toJson = "";

        url = CHECKTOKEN;
    	try {
			StringEntity entity = new StringEntity(token);
			succesfulCheck = post(url, entity);
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
    	
    	Cart newCar = new Cart();
    	List <Productadapter> productEntities = null;
	    
	    newCar.setDate(date);
	    newCar.setIdUser(idUser);
	    for(ProductAdapter p:products) {
	    	Productadapter productEnt= new Productadapter();
	    	productEnt.setAmount(p.getAmount());
	    	productEnt.setDescription(p.getDescription());
	    	productEnt.setImage(p.getImage());
	    	productEnt.setKeywords(p.getKeywords());
	    	productEnt.setName(p.getName());
	    	productEnt.setPrice(p.getPrice());
	    	productEnt.setType(p.getType());
	    	productEntities.add(productEnt);
	    } 
	    newCar.setProductadapters(productEntities);
	   
	    Cart car = entityManager.find(Cart.class, newCar.getIdcart());
	    if (car == null) {
	    entityManager.persist(newCar);
	    System.out.println("insertado");
	    } else
	    System.out.println("mal");
	
    	
//      TODO: Connect payment
//      url = "http://localhost:8080/payments-web-0.0.1-SNAPSHOT/Pay"; 
//      toJson = "{ \"StripeToken\":\""+stripeToken.getId()+"\"}";
//    	try {
//    		StringEntity entity = new StringEntity(toJson);
//			succesfulPayment = post(url, entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
    	succesfulPayment = true;
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

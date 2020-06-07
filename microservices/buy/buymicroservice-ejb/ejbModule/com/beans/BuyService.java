package com.beans;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.classes.AuthToken;
import com.classes.CartAdapter;
import com.classes.CartProductAdapter;
import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;
import com.entities.Product;
import com.google.gson.Gson;

import model.Car;
import model.CartProduct;

/**
 * Session Bean implementation class BuyService
 */
@Stateless
@LocalBean
public class BuyService implements BuyServiceRemote, BuyServiceLocal {
	
	private final String CHECKTOKEN = "http://localhost:8080/usersmicroservice-web-0.0.1-SNAPSHOT/CheckToken";
	
	public String post(String url, StringEntity entity) throws Exception {
		HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        	int code = response.getStatusLine().getStatusCode();
        	String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
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
	public boolean buy(List<CartProduct> products, String token, StripeToken stripeToken, DeliveryInfo deliveryInfo, FinancialInfo financialInfo ,String productsString) {

		System.out.println("Servicio de compra inicial");
        String destinyAddress = deliveryInfo.getAddress();
        boolean succesfulPayment = false;
        boolean succesfulCheck = true;
        String url = "";
        String toJson = "";
        String username="";

        url = CHECKTOKEN;
    	try {
			StringEntity entity = new StringEntity(token);
			succesfulCheck = post(url, entity)!=null;
			Gson gson=new Gson();
			AuthToken at=gson.fromJson(token, AuthToken.class);
			username=at.getUsername();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        int total = 0;
        String GetPriceUrl="http://127.0.0.1:8080/stockmicroservice-web-0.0.1-SNAPSHOT/GetPrice";
        try {
        	String jsonString="{\"products\":"+productsString+"}";
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
			Car car =new Car();
    		car.setUsername(username);
    		car.setDate(new Timestamp(System.currentTimeMillis()));
    		car.save();
    		for(CartProduct p:products) {
            	Product c = Product.getProduct(p.getId());
            	if( c!= null) {
        	    	CartProduct productEnt= new CartProduct();
        	    	productEnt.setProductId(c.getId());
        	    	productEnt.setAmount(p.getAmount());
        	    	productEnt.setDescription(c.getDescription());
        	    	productEnt.setImage(c.getImage());
        	    	productEnt.setKeywords(c.getKeywords());
        	    	productEnt.setName(c.getName());
        	    	productEnt.setPrice(c.getPrice());
        	    	productEnt.setCar(car);
        	    	boolean please = productEnt.save();
            	}
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

	@Override
	public List<CartAdapter> getPurchases(String username) {	
		System.out.println("Sacando los productos del carrito y el carrito");
		List<Car> allCars = Car.getCars();
		List<CartAdapter> carts = new ArrayList<CartAdapter>();	
		for (Car car : allCars) {
			if(car.getUsername().contentEquals(username)) {
				CartAdapter cartTemp = new CartAdapter();
				cartTemp.setUsername(username);
				cartTemp.setDate(car.getDate());
				cartTemp.setId(car.getId());
				carts.add(cartTemp);
				for (CartProduct cartProduct : car.getProducts()) {
					CartProductAdapter temp = new CartProductAdapter();
					temp.setId(cartProduct.getProductId());
					temp.setAmount(cartProduct.getAmount());
					temp.setName(cartProduct.getName());
					temp.setImage(cartProduct.getImage());
					temp.setPrice(cartProduct.getPrice());
					cartTemp.addProduct(temp);
				}
			}
		}
		return carts;
	}

}

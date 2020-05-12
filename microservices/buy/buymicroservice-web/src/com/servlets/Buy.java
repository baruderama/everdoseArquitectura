package com.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.beans.BuyService;
import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.ProductAdapter;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	BuyService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
		String body = IOUtils.toString(request.getReader());
		Cookie[] cookies = null;
		Cookie cookie = null;
		cookies = request.getCookies();
		
		String token = null;
		System.out.println("Cookies");
		for (int i = 0; i < cookies.length; i++) {
			System.out.println("Cookie");
            cookie = cookies[i];
            
            if( cookie.getName().contentEquals("auth_token")) {
            	token = cookie.getValue();
            }
         }
		
		if(token != null) {
			
	        Gson g = new Gson(); 
	        JSONObject json = new JSONObject(body);
	        String stripeToken_str = json.get("stripeToken").toString();
	        String productsList_str = json.get("products").toString();
	        String deliveryInformation_str = json.get("delivery_information").toString();
	        String financialInformation_str = json.get("financial_information").toString();

	        StripeToken stripeToken = g.fromJson(stripeToken_str, StripeToken.class);
	        DeliveryInfo deliveryInfo = g.fromJson(deliveryInformation_str, DeliveryInfo.class);
	        FinancialInfo financialInfo = g.fromJson(financialInformation_str, FinancialInfo.class);
	        
	        
			Type listType = new TypeToken<ArrayList<ProductAdapter>>(){}.getType();
			ArrayList<ProductAdapter> products = new Gson().fromJson(productsList_str, listType);
			String destiny_address=json.get("destiny_address").toString();
			String productsStr=json.get("products").toString();
			bean.buy(products, token, stripeToken, deliveryInfo, financialInfo);
		}
	}
	
	@Override
	  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	
	  private void setAccessControlHeaders(HttpServletResponse resp) {
		  System.out.println("Setting headers");
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	      resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
	      resp.setHeader("Access-Control-Allow-Credentials", "true");
	      resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	  }


}

package com.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.Bean;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.beans.DeliveryService;
import com.entities.DeliveryProduct;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utils.Utils;

/**
 * Servlet implementation class Deliver
 */
@WebServlet("/DeliverOrder")
public class Deliver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	DeliveryService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deliver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Deliver!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Type type = new TypeToken<List<DeliveryProduct>>(){}.getType();
		JSONObject json=new JSONObject(Utils.getJSON(request));
		System.out.println(json.toString());
		String destinAddress=json.get("destiny_address").toString();
	    String products_json =json.get("products").toString();
		List<DeliveryProduct> products = new Gson().fromJson( products_json, type );
		
		boolean deliveryCreated = false;
		
		if ( products != null) {
			deliveryCreated = bean.deliver( destinAddress, products);
		}
		else {
			deliveryCreated = false;
		}
		
		if ( deliveryCreated ) {
			response.getWriter().append( Boolean.toString( deliveryCreated ) );
		}
		else {
			response.setStatus( response.SC_INTERNAL_SERVER_ERROR );
			response.getWriter().append( "No se creó el delivery" );
		}
		
	}

}

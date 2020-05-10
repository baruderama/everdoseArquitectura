package com.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.beans.DeliveryService;
import com.entities.DeliveryOrder;
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
		Gson gson = new Gson();
		DeliveryOrder deliveryOrder = gson.fromJson(request.getReader(), DeliveryOrder.class);
	    
		boolean deliveryCreated = false;
		
		if ( deliveryOrder.getProducts() != null) {
			deliveryCreated = bean.deliver( deliveryOrder.getDestin_address(), deliveryOrder.getProducts());
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

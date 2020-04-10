package com.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.Bean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.DeliveryService;
import com.entities.DeliveryProduct;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class Deliver
 */
@WebServlet("/Deliver")
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
		String originAddress = request.getParameter("originAddress");
		String destinAddress = request.getParameter("destinAddress");
		String productsString = request.getParameter("deliveryProducts");
		boolean fromStock = Boolean.getBoolean( request.getParameter("fromStock") );
		List<DeliveryProduct> products = new Gson().fromJson( productsString, type );
		
		boolean deliveryCreated = false;
		
		if ( products != null) {
			deliveryCreated = bean.deliver(originAddress, destinAddress, products);
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

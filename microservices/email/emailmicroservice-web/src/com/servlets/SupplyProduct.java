package com.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.beans.EmailService;
import com.classes.EmailOrder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class SupplyProduct
 */
@WebServlet("/SupplyProduct")
public class SupplyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	EmailService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplyProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Supply product!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		EmailOrder emailOrder = gson.fromJson(request.getReader(), EmailOrder.class);

		System.out.println("Post email");
		bean.supplyProduct(emailOrder.getEmail(), emailOrder.getName() , emailOrder.getProducts());
		
		doGet(request, response);
	}

}

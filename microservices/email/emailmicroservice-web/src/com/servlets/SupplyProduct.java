package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.EmailService;

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
		// TODO Auto-generated method stub
		String products = request.getParameter("products");
		String email = request.getParameter("email");
		String supplierName = request.getParameter("supplierName");
		bean.supplyProduct(email, supplierName , products);
		
		doGet(request, response);
	}

}

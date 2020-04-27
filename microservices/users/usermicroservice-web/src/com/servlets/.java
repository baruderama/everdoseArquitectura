package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.StockService;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	StockService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Adding product if POST");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		float price = Float.valueOf( request.getParameter("price") ); 
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String image = request.getParameter("image");
		int threshold = Integer.valueOf( request.getParameter("threshold") );
		int amount = Integer.valueOf( request.getParameter("amount") );
		
		bean.addProduct(name, description, location, image, price, threshold, amount);
		
		doGet(request, response);
	}

}

package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.SupplierService;

/**
 * Servlet implementation class AddProductToSupplier
 */
@WebServlet("/AddProductToSupplier")
public class AddProductToSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB 
	SupplierService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductToSupplier() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Add product to supplier!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int supplier = Integer.valueOf( request.getParameter("supplier") );
		String keywords = request.getParameter("keywords");
		String description = request.getParameter("description");
		float price = Float.valueOf( request.getParameter("price"));
		bean.addProductToSupplier(supplier, name, keywords, description, price);
		doGet(request, response);
	}

}

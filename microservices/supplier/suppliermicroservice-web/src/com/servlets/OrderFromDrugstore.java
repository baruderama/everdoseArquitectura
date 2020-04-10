package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.SupplierService;
import com.entities.ProductFromDrugstore;

/**
 * Servlet implementation class OrderFromDrugstore
 */
@WebServlet("/OrderFromDrugstore")
public class OrderFromDrugstore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	@EJB 
	SupplierService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderFromDrugstore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String keywords = request.getParameter("keywords");
		int amount = Integer.valueOf( request.getParameter("amount" ) );
		String destin = request.getParameter("destinaddress");
		ProductFromDrugstore product = bean.orderFromDrugstore(name, keywords, amount, destin);
		if ( product != null) {
			System.out.println("An order from drugstore was created...");
			System.out.println("Product: "+product.getName());
			System.out.println("Price: "+product.getPrice());
			System.out.println("Drugstore: "+product.getDrugstore_id());
		}
		doGet(request, response);
	}

}

package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.SupplierService;
import com.entities.ProductFromSupplier;

/**
 * Servlet implementation class OrderFromSupplier
 */
@WebServlet("/OrderFromSupplier")
public class OrderFromSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB 
	SupplierService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderFromSupplier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Order from supplier!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String keywords = request.getParameter("keywords");
		int amount = Integer.valueOf( request.getParameter("amount" ) );
		ProductFromSupplier product = bean.orderFromSupplier(name, keywords, amount);
		if ( product != null) {
			System.out.println("An order from supplier was created...");
			System.out.println("Product: "+product.getName());
			System.out.println("Price: "+product.getPrice());
			System.out.println("Supplier: "+product.getSupplier_id());
		}
		else {
			System.out.println("No product was found for the given requirements.");	
		}
		doGet(request, response);
	}

}

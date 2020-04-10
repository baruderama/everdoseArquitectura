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
 * Servlet implementation class RemoveProduct
 */
@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	StockService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Removing product if POST...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.valueOf( request.getParameter("id") );
		bean.removeProduct(id);
		doGet(request, response);
	}

}

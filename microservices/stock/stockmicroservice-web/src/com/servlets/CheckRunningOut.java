package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.StockService;
import com.entities.Product;
import com.google.gson.Gson;

/**
 * Servlet implementation class CheckRunningOut
 */
@WebServlet("/CheckRunningOut")
public class CheckRunningOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	StockService bean;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRunningOut() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = bean.checkRunningOut();
		String productsJsonString = new Gson().toJson(products);	
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(productsJsonString);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

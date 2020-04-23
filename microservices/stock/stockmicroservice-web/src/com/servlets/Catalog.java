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
import com.utils.ProductAdapter;

/**
 * Servlet implementation class Catalog
 */
@WebServlet("/Catalog")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB 
	StockService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ProductAdapter> products = bean.getCatalog();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

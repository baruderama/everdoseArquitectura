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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String keywords=request.getParameter("keywords");
		System.out.println(keywords);
		List<ProductAdapter> products = bean.getCatalog(keywords);
		Gson gson = new Gson();
		products.forEach(p -> System.out.println(p.getName()));
		String productsJsonString = gson.toJson(products);
		
		PrintWriter out = response.getWriter();
		out.print(productsJsonString);
		out.flush();
		out.close();
		System.out.println("65");
		setAccessControlHeaders(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		String keywords=request.getParameter("keywords");
		List<ProductAdapter> products = bean.getCatalog(keywords);
		Gson gson = new Gson();
		products.forEach(p -> System.out.println(p.getName()));
		String productsJsonString = gson.toJson(products);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(productsJsonString);
		out.flush();
		out.close();
	}
	
	@Override
	  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	
	  private void setAccessControlHeaders(HttpServletResponse resp) {
		  System.out.println("Setting headers");
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	      resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
	      resp.setHeader("Access-Control-Allow-Credentials", "true");
	      resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	  }

}

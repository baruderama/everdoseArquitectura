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
 * Servlet implementation class AddProduct
 */
@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	StockService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Product> products = bean.getProducts();
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
		float price=0;
		int threshold=0; 
		int amount=0;
		try {
			price = Float.valueOf( request.getParameter("price") );
			threshold = Integer.valueOf( request.getParameter("threshold") );
			amount = Integer.valueOf( request.getParameter("amount") );
		}catch(Exception e) {
			response.getWriter().append("invalid format");
			return;
		}
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String image = request.getParameter("image");
		
		String keywords=request.getParameter("keywords");
		
		bean.addProduct(name, description, location, image, price, threshold, amount,keywords);
		
		response.getWriter().append("product added");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Float price=(float) -1;
		Integer threshold=-1; 
		Integer amount=-1;
		int id=-1;
		try {
			id=Integer.valueOf( request.getParameter("product_id") );
		}catch(Exception e) {
				response.getWriter().append("invalid format");
				return;
		}
		try{price = Float.valueOf( request.getParameter("price") );}catch(Exception e){price=null;}
		try{threshold = Integer.valueOf( request.getParameter("threshold") );}catch(Exception e){threshold=null;}
		try{amount = Integer.valueOf( request.getParameter("amount") );}catch(Exception e){amount=null;}
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String image = request.getParameter("image");
		
		String keywords=request.getParameter("keywords");
		
		bean.modifyProduct(id,name, description, location, image, price, threshold, amount,keywords);
		
		response.getWriter().append("product modifyed");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int id=0;
		try {
			id=Integer.valueOf(request.getParameter("id"));
		}catch (Exception ignore) { 
			response.getWriter().append("no id");
			return;
		}
		if(bean.removeProduct(id)) {
			response.getWriter().append("success");
		}else {
			response.getWriter().append("failed");
		}	
	}

}

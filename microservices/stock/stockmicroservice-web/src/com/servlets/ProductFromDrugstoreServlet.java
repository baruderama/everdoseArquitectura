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
import com.entities.ProductFromDrugstore;
import com.google.gson.Gson;

/**
 * Servlet implementation class ProductFromDrugstoreServlet
 */
@WebServlet("/ProductFromDrugstoreServlet")
public class ProductFromDrugstoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB 
	StockService bean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductFromDrugstoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ProductFromDrugstore> products = bean.getProductsFromDrugstore();
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
		float price =0;
		int drugstore_id=0;
		try {
			price = Float.valueOf( request.getParameter("price") );
			drugstore_id=Integer.valueOf(request.getParameter("drugstore_id"));
		}catch(Exception e) {
			response.getWriter().append("incorret format");
			return;
		}
		String name = request.getParameter("name");
		
		String description = request.getParameter("description");
		String keywords = request.getParameter("keywords");
		
		
		bean.addProductFromDrugstore(name, description, keywords, price,drugstore_id);
		
		response.getWriter().append("product added");
	}

}

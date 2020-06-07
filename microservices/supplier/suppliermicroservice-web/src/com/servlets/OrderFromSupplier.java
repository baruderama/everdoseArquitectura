package com.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.beans.SupplierService;
import com.classes.SupplierProduct;
import com.entities.ProductFromSupplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
		String body = IOUtils.toString(request.getReader());
		System.out.println(body);
		JSONObject json = new JSONObject(body);
        String productsList_str = json.get("products").toString();
        Type listType = new TypeToken<ArrayList<SupplierProduct>>(){}.getType();
        Gson gson = new Gson();
		List<SupplierProduct> products = gson.fromJson(productsList_str, listType);
		int orderedProducts = bean.orderFromSupplier(products);
//		if ( product != null) {
//			System.out.println("An order from supplier was created...");
//			System.out.println("Product: "+product.getName());
//			System.out.println("Price: "+product.getPrice());
//			System.out.println("Supplier: "+product.getSupplier_id());
//		}
//		else {
//			System.out.println("No product was found for the given requirements.");	
//		}
		doGet(request, response);
	}

}

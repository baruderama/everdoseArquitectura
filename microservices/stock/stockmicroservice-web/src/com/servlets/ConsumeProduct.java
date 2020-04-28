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

import org.json.JSONObject;

import com.beans.StockService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.untils.Utils;
import com.utils.ProductAdapter;

/**
 * Servlet implementation class ConsumeProduct
 */
@WebServlet("/ConsumeProduct")
public class ConsumeProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	StockService bean;
	
    public ConsumeProduct() {
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
		// TODO Auto-generated method stub
		JSONObject jsonObj=new JSONObject(Utils.getJSON(request));
		String destiny_address=jsonObj.get("destiny_address").toString();
		String json=jsonObj.get("products").toString();
		Type listType = new TypeToken<ArrayList<ProductAdapter>>(){}.getType();
		List<ProductAdapter> products = new Gson().fromJson(json, listType);
		if(bean.consumeProducts(products,destiny_address)) {
			response.getWriter().append("succes");
		}else {
			response.getWriter().append("failed");
		}
	}

}

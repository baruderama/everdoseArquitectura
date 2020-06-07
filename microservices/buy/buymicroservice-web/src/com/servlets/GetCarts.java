package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.BuyService;
import com.classes.CartAdapter;
import com.google.gson.Gson;
import com.utils.Token;

/**
 * Servlet implementation class GetCarts
 */
@WebServlet("/GetCarts")
public class GetCarts extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@EJB 
	BuyService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCarts() {
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
        setAccessControlHeaders(response);
		Cookie[] cookies = null;
		Cookie cookie = null;
		cookies = request.getCookies();
		
		String token_str = null;
		System.out.println("Cookies");
		for (int i = 0; i < cookies.length; i++) {
			System.out.println("Cookie");
            cookie = cookies[i];    
            if( cookie.getName().contentEquals("auth_token")) {
            	System.out.println("Hay token");
            	token_str = cookie.getValue();
            }
         }
		
		if(token_str != null) {
			System.out.println(74);
			System.out.println(token_str);
			Gson gson = new Gson();
			Token token = gson.fromJson(token_str, Token.class);
			System.out.println(token.getUsername());
			if(token != null) {
				List<CartAdapter> products = bean.getPurchases(token.getUsername());
				System.out.println(77);
				System.out.println(products);
				String productsJSON = gson.toJson(products);
				PrintWriter out = response.getWriter(); 
				out.print(productsJSON);
				out.flush();
			}
		}
	}
	
	@Override
	  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	
	  private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	      resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
	      resp.setHeader("Access-Control-Allow-Credentials", "true");
	      resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	  }

}

package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.UserService;
import com.google.gson.Gson;
import com.utils.Token;
import com.utils.Utils;

/**
 * Servlet implementation class IsAuthenticated
 */
@WebServlet("/IsAuthenticated")
public class IsAuthenticated extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UserService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsAuthenticated() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            	token_str = cookie.getValue();
            }
         }
		
		if(token_str != null) {
			String token_json=Utils.getJSON(request);
			Gson gson=new Gson();
			Token token=gson.fromJson(token_json, Token.class);
			bean.checkToken(token);
//			TODO: Que retorne un User
		}
		doGet(request, response);
	}
	
	  private void setAccessControlHeaders(HttpServletResponse resp) {
		  System.out.println("Setting headers");
	      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	      resp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
	      resp.setHeader("Access-Control-Allow-Credentials", "true");
	      resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	  }

}

package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.beans.UserService;
import com.google.gson.Gson;
import com.classes.Token;

/**
 * Servlet implementation class GetToken
 */
@WebServlet("/GetToken")
public class GetToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	UserService bean;
	
    public GetToken() {
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
		
//		https://stackoverflow.com/questions/49673010/how-to-receive-form-parameters-values-in-server
		String data = IOUtils.toString(request.getReader());
		JSONObject json = new JSONObject(data);
		String password= json.get("password").toString();
		String username= json.get("username").toString();
		System.out.println("data");
		System.out.println(username);
		System.out.println(password);
		Token token=bean.getToken(username, password);
		if(token!=null){
			String token_json=new Gson().toJson(token);
			Cookie cookie = new Cookie("token",token.toJson());
			response.addCookie(cookie);
			response.getWriter().append(token_json);
		}else {
			response.getWriter().append("failed");
		}
	}

}

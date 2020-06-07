package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.UserService;
import com.google.gson.Gson;
import com.utils.Token;
import com.utils.Utils;



/**
 * Servlet implementation class CheckToken
 */
@WebServlet("/CheckToken")
public class CheckToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@EJB
	UserService bean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckToken() {
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
		String token_json=Utils.getJSON(request);
		Gson gson=new Gson();
		Token token=gson.fromJson(token_json, Token.class);
		if(bean.checkToken(token)) {
			response.getWriter().append("valid");
		}else {
			response.setStatus(401);
		}
	}

}

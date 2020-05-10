package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.beans.UserService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	UserService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String newPassword=request.getParameter("newPasword");
		String lastname=request.getParameter("lastname");
		String phone=request.getParameter("phone");
		if(bean.getToken(username, password)!=null) {
			if(bean.updateUser(username,email,name,newPassword,lastname,phone)) {
				response.getWriter().append("success");
			}else {
				response.getWriter().append("failed");
			}
			
		}else {
			response.setStatus(401);;
		}
		
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String body = IOUtils.toString(request.getReader());
		Gson g = new Gson(); 
        JSONObject json = new JSONObject(body);
        
		String username=json.get("username").toString();
		String email=json.get("email").toString();
		String firstname=json.get("firstname").toString();
		String lastname=json.get("lastname").toString();
		String password=json.get("password").toString();
		String phone=json.get("phone").toString();
		System.out.println(67);
		if(bean.createUser(username, email, firstname, password, lastname, phone)) {
			response.setStatus(HttpServletResponse.SC_CREATED);
		}else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(bean.getToken(username, password)!=null) {
			if(bean.deleteUser(username)) {
				response.getWriter().append("success");
			}else {
				response.getWriter().append("failed");
			}
		}else {
			response.setStatus(401);;
		}
	
	}

}

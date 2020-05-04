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
		if(bean.getToken(username, password)!=null) {
			if(bean.updateUser(username,email,name,newPassword)) {
				response.getWriter().append("success");
				
			}else {
				response.getWriter().append("failed");
			}
			
		}else {
			response.getWriter().append("incorrect password");
		}
		
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = IOUtils.toString(request.getReader());
		JSONObject json = new JSONObject(data);
		String username=json.get("username").toString();
		String email=json.get("email").toString();
		String name=json.get("name").toString();
		String password=json.get("password").toString();
		
//		TO-DO: Phone 
		String phone=request.getParameter("phone");
		
		if(bean.createUser(username, email, name, password)) {
			response.getWriter().append("user created");
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("failed");
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
			response.getWriter().append("incorrect password");
		}
	
	}

}

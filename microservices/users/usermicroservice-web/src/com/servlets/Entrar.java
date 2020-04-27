package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.StockService;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class Entrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	UserService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entrar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		List <Usuario> usuario=bean.findUsuario(name,password);
		if(usuario.size()==1) {
			response.getWriter().append("Existe");
		}
		
		response.getWriter().append("t");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request, response);
	}

}

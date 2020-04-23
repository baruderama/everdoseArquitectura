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
import com.entities.Drugstore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Drugstore
 */
@WebServlet("/Drugstore")
public class DrugstoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@EJB 
	StockService bean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrugstoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Drugstore> drugstores= bean.getDrugstores();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String drugstoresJsonString = gson.toJson(drugstores);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(drugstoresJsonString);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String phone =request.getParameter("phone");
		String uri=request.getParameter("uri");
		if(bean.addDrugstore(address, email, name, phone, uri)) {
			response.getWriter().append("success");
		}else {
			response.getWriter().append("failed");
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		try {
			id=Integer.valueOf(request.getParameter("drugstore_id"));
		}catch (Exception ignore) { 
			response.getWriter().append("no id");
			return;
		}
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String phone =request.getParameter("phone");
		String uri=request.getParameter("uri");
		if(bean.modifyDrugstore(id,address, email, name, phone, uri)) {
			response.getWriter().append("success");
		}else {
			response.getWriter().append("failed");
		}	
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		try {
			id=Integer.valueOf(request.getParameter("drugstore_id"));
		}catch (Exception ignore) { 
			response.getWriter().append("no id");
			return;
		}
		if(bean.deleteDrugstore(id)) {
			response.getWriter().append("success");
		}else {
			response.getWriter().append("failed");
		}	
	}

}

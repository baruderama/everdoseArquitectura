package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.SupplierService;
import com.entities.SupplierOrder;

/**
 * Servlet implementation class PaySuppliers
 */
@WebServlet("/PaySuppliers")
public class PaySuppliers extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB 
	SupplierService bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaySuppliers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HashMap< Integer, Float> payments = bean.paySuppliers();
		payments.forEach(( supplier_id, payment ) -> {
			System.out.println("We payed "+payment+" to "+supplier_id);
		});
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

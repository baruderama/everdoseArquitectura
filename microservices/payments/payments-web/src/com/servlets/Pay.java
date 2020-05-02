package com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.json.HTTP;
import org.json.JSONException;

import com.beans.PaymentsService;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @EJB
    PaymentsService bean;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
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
    	
        StringBuffer jb = new StringBuffer();
        String line = null;
        String body = IOUtils.toString(request.getReader());
        System.out.println(body);
        JSONObject json = new JSONObject(body);
        System.out.println(json.get("test"));
//        StripeToken a = json.getString("stripeToken");

    }

}

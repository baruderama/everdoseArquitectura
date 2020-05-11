package com.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.SupplierServiceRemote;

/**
 * Servlet implementation class Payments
 */
@WebServlet("/Payments")
public class Payments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		//jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8090");
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "micro");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "micro");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		
		String namespace = "ejb:";
		String appName = "suppliermicroservice-ear-0.0.1-SNAPSHOT";
		String moduleName = "suppliermicroservice-ejb-0.0.1-SNAPSHOT";
		String distinctName = "";
		String beanName = "SupplierService";
		String viewClassName = SupplierServiceRemote.class.getName();
		SupplierServiceRemote seriviciosSupplier = null;
		Context ctx;
		try {
			ctx= new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
		ctx = new InitialContext(jndiProperties);
		seriviciosSupplier = (SupplierServiceRemote) ctx.lookup(
		namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
		} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

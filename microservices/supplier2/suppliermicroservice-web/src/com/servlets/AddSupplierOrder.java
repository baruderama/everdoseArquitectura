package com.servlets;

import java.io.IOException;
import java.util.List;
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
import com.entities.Supplierorder;

/**
 * Servlet implementation class AddSupplierOrder
 */
@WebServlet("/AddSupplierOrder")
public class AddSupplierOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSupplierOrder() {
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
		
		//Supplierorder order=seriviciosSupplier.findSupplierOrder(1);
		//String supOr=seriviciosSupplier.addSupplierOrder("med1", 1, 1, 12, 3);
		//String supOr2=seriviciosSupplier.updateSupplierOrder(1, "med2", 2, 2, 13, 3, 1);
		String supOr3=seriviciosSupplier.deleteSupplierOrder(1);
		//List<Supplierorder> suppOrList= seriviciosSupplier.getAllSupplierOrders();
		
		/*
		for (Supplierorder sup : suppOrList) {
			 System.out.println(sup.getProductName());
			 response.getWriter().append("Served at: ").append(sup.getProductName());
			 //response.getWriter().append("Served at: ").append(user.getUsername());
			 }
			 System.out.println("Size: " + suppOrList.size());
			*/ 
		
		response.getWriter().append("Served at: ").append(supOr3);
	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

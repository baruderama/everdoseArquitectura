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

import com.beans.BuyServicesRemote;
import com.entities.Car;

/**
 * Servlet implementation class AddCar
 */
@WebServlet("/AddCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCar() {
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
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "user");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "user");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		
		String namespace = "ejb:";
		String appName = "buymicroservice-ear-0.0.1-SNAPSHOT";
		String moduleName = "buymicroservice-ejb-0.0.1-SNAPSHOT";
		String distinctName = "";
		String beanName = "BuyServices";
		String viewClassName = BuyServicesRemote.class.getName();
		BuyServicesRemote seriviciosBuy = null;
		Context ctx;
		try {
			ctx= new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
		ctx = new InitialContext(jndiProperties);
		seriviciosBuy = (BuyServicesRemote) ctx.lookup(
		namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
		} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//String car=seriviciosBuy.addCarOrder("1/1/12", 1, "med1", 13, 1, 2);
		//Car car=seriviciosBuy.findCar(1);
		String car=seriviciosBuy.updateCar(1, "2/2/12", 2, "med2", 14, 2, 3);
		//List<Car> carList=seriviciosBuy.getAllCars();
		//String car=seriviciosBuy.deleteCar(1);
		
		/*
		for (Car sup : carList) {
			 System.out.println(sup.getCarProductName());
			 response.getWriter().append("Served at: ").append(sup.getCarProductName());
			 //response.getWriter().append("Served at: ").append(user.getUsername());
			 }
			 System.out.println("Size: " + carList.size());
			 */
		
		response.getWriter().append("Served at: ").append(car);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

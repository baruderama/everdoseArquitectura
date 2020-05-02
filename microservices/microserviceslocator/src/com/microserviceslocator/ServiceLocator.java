package com.microserviceslocator;

import java.io.BufferedReader;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.beans.PaymentsServiceRemote;

import javax.naming.Context;

public class ServiceLocator {
	  static BufferedReader brConsoleReader = null; 
	  static Properties props;
	  static InitialContext ctx;
	  
	  public ServiceLocator() {
		  props = new Properties();
		  props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		  props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		  props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		  props.put("jboss.naming.client.ejb.context", true);
	  }
	   
	  public PaymentsServiceRemote getPaymenntsServiceRemote() {
		  try {
			ctx = new InitialContext(props);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          PaymentsServiceRemote bean;
			try {
				bean = (PaymentsServiceRemote)ctx.lookup("ejb:paymentsmicroservice-ear-0.0.1-SNAPSHOT/payments-ejb/PaymentsService!com.beans.PaymentsServiceRemote?stateful");
				return bean;
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          return null;
	  }
	 
}

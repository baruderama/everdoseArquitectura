package com.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Drugstore;
import com.entities.Supplier;

/**
 * Session Bean implementation class SupplierService
 */
@Stateless
@LocalBean
public class SupplierService implements SupplierServiceRemote, SupplierServiceLocal {

    /**
     * Default constructor. 
     */
    public SupplierService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean addSupplier(String name, String address, String phone, String email, String uri) {
boolean succesfulltransaction = false;
		
		try {
			
			Supplier supplier = new Supplier();
			supplier.setName(name);
			supplier.setAddress(address);
			supplier.setPhone(phone);
			supplier.setEmail(email);
			supplier.setUri(uri);
			
			supplier.save();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return succesfulltransaction;
	}

	@Override
	public boolean addDrugstore(String name, String address, String phone, String email, String uri) {
		boolean succesfulltransaction = false;
		
		try {
			
			Drugstore drugstore = new Drugstore();
			drugstore.setName(name);
			drugstore.setAddress(address);
			drugstore.setPhone(phone);
			drugstore.setEmail(email);
			drugstore.setUri(uri);
			
			drugstore.save();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return succesfulltransaction;
	}

	@Override
	public boolean addSupplierfinancialInformation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean order(String name, String keywords, String amount) {
		List<Supplier> suppliers = Supplier.getSuppliers();
		for (Supplier supplier : suppliers) {
			String uri = supplier.getUri();
//			try {
//				InputStream is = new URL(uri).openStream();
//				BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//		      String jsonText = readAll(rd);
//		      JSONObject json = new JSONObject(jsonText);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			TO-DO: De la Uri, ver el catálogo y consultar el precio, del supplier con el precio más bajo se crea la orden. 
			String email = supplier.getEmail();
//			TO-DO: Se va al servicio de email y se pide el producto.
		}
		return false;
	}

	@Override
	public List<Supplier> paySuppliers() {
		// TODO Auto-generated method stub
		return null;
	}

}

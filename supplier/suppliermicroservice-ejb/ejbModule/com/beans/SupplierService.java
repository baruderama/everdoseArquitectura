package com.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Drugstore;
import com.entities.ProductFromDrugstore;
import com.entities.ProductFromSupplier;
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
	public boolean orderFromSupplier(String name, String keywords, int amount) {
		List<ProductFromSupplier> products = ProductFromSupplier.getProductsFromSuppliers();
		String[] eachkeyword = keywords.split(",");
//		TODO: Si cumple todas las keywords
		float min = 999999;
		ProductFromSupplier chosenProduct= null;
		for (ProductFromSupplier product : products) {
			if ( product.getName() == product.getName() && product.getPrice() < min) {
				chosenProduct = product;
			}
		}
//		TODO: Llama al servicio de email para pedir el producto.
		return false;
	}

	@Override
	public List<Supplier> paySuppliers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean orderFromDrugstore(String name, String keywords, int amount, String destin_address) {
		List<ProductFromDrugstore> products = ProductFromDrugstore.getProductsFromDrugstores();
		String[] eachkeyword = keywords.split(",");
//		TODO: Si cumple todas las keywords
		float min = 999999;
		ProductFromDrugstore chosenProduct= null;
		for (ProductFromDrugstore product : products) {
			if ( product.getName() == product.getName() && product.getPrice() < min) {
				chosenProduct = product;
			}
		}
//		TODO: Llama al servicio de delivery para llevar el producto
		return true;
	}

	@Override
	public boolean addProductToSupplier(int supplier_id, String name, String keywords, String description, float price) {
		Supplier supplier = Supplier.getSupplier(supplier_id);
		if ( supplier != null ) {
			ProductFromSupplier product = new ProductFromSupplier();
			product.setName(name);
			product.setSupplier_id(supplier_id);
			product.setDescription(description);
			product.setKeywords(keywords);
			product.setPrice(price);
			product.save();
			System.out.println("The product from the supplier was succesfully added.");
			return true;
		}
		System.out.println("Supplier wasn't found");
		return false;
	}

	@Override
	public boolean addProductToDrugstore(int drugstore_id, String name, String keywords, String description, float price) {
		Drugstore drugstore = Drugstore.getDrugstore(drugstore_id);
		if (drugstore != null) {
			ProductFromDrugstore product = new ProductFromDrugstore();
			product.setName(name);
			product.setDrugstore_id(drugstore_id);
			product.setDescription(description);
			product.setKeywords(keywords);
			product.setPrice(price);
			product.save();
			return true;
		}
		return false;
	}

}

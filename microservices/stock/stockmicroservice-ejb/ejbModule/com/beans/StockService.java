package com.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Drugstore;
import com.entities.Product;

/**
 * Session Bean implementation class StockService
 */
@Stateless
@LocalBean
public class StockService implements StockServiceRemote, StockServiceLocal {

    /**
     * Default constructor.
     */
    public StockService() {

    }

	@Override
	public List<Product> getCatalog() {
		return Product.getProducts();
	}

	@Override
	public boolean addProduct( String name, String description, String location, String image, float price, int threshold, int amount ) {

		boolean succesfulltransaction = false;

		try {

			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setAmount(amount);
			product.setPrice(price);
			product.setImage(image);
			product.setThreshold(threshold);
			product.setLocation(location);
			product.save();

			succesfulltransaction = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return succesfulltransaction;
	}

	@Override
	public boolean removeProduct( int id ) {
		boolean succesfulltransaction = false;
		try {
			Product product = Product.getProduct(id);
			product.removeProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return succesfulltransaction;
	}

	@Override
	public List<Product> checkRunningOut() {
		// TODO Auto-generated method stub
		ArrayList<Product> productsRunningOut = new ArrayList<Product>();
		try {
			List<Product> products = Product.getProducts();
			for (Product product : products) {
				if (product.getAmount() < product.getThreshold()) {
					productsRunningOut.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productsRunningOut;
	}

	@Override
	public boolean addDrugstore(String address, String email, String name, String phone, String uri) {
		Drugstore drugstore=new Drugstore();
		drugstore.setAddress(address);
		drugstore.setEmail(email);
		drugstore.setName(name);
		drugstore.setPhone(phone);
		drugstore.setUri(uri);
		return drugstore.save();
	}

	@Override
	public List<Drugstore> getDrugstores() {
		return Drugstore.getDrugstores();
	}

	@Override
	public boolean modifyDrugstore(int id, String address, String email, String name, String phone, String uri) {
		return Drugstore.UpdateDrugstoreById(id, address, email, name, phone, uri);
		
	}

	@Override
	public boolean deleteDrugstore(int id) {
		return Drugstore.deleteById(id);
	}

}

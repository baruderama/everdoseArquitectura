package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Drugstore;
import com.entities.Product;
import com.entities.ProductFromDrugstore;
import com.utils.ProductAdapter;

@Local
public interface StockServiceLocal {
	public List<ProductAdapter> getCatalog(String keywords);
	
	public List<Product> checkRunningOut();	

	public List<ProductFromDrugstore> getProductsFromDrugstore();
	public boolean removeProductFromDrugstore(int id);
	public boolean addProductFromDrugstore(String name, String description,String keywords,float price,int drugstore_id);
	public boolean modifyProductFromDrugstore(int id, String name, String description, Float price, String keywords);
	
	public List<Product> getProducts();
	public boolean addProduct(String name, String description, String location, String image, float price, int threshold, int amount,String keyword );
	public boolean removeProduct(int id);
	public boolean modifyProduct(int id,String name, String description, String location, String image, Float price, Integer threshold, Integer amount,String keyword );
	
	public boolean addDrugstore(String address, String email,String name,String phone,String uri);
	public List<Drugstore> getDrugstores();
	public boolean modifyDrugstore(int id,String address, String email,String name,String phone,String uri);
	public boolean deleteDrugstore(int id);
	
	
}


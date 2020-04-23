package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Drugstore;
import com.entities.Product;

@Local
public interface StockServiceLocal {
	public List<Product> getCatalog();
	public boolean addProduct(String name, String description, String location, String image, float price, int threshold, int amount );
	public boolean removeProduct(int id);
	public List<Product> checkRunningOut();
	public boolean addDrugstore(String address, String email,String name,String phone,String uri);
	public List<Drugstore> getDrugstores();
	public boolean modifyDrugstore(int id,String address, String email,String name,String phone,String uri);
	public boolean deleteDrugstore(int id);
}


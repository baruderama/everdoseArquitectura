package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Product;

@Local
public interface StockServiceLocal {
	public List<Product> getProducts();
	public boolean addProduct(String name, String description, String location, String image, float price, int threshold, int amount );
	public boolean removeProduct(int id);
	public boolean checkRunningOut();
}

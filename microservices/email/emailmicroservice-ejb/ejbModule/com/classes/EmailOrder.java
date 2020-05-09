package com.classes;

import java.util.List;

public class EmailOrder {
	private String email;
	private String name;
	private List<SupplierProduct> products;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SupplierProduct> getProducts() {
		return products;
	}
	public void setProducts(List<SupplierProduct> products) {
		this.products = products;
	}
}

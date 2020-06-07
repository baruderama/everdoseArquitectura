package com.classes;

import java.util.ArrayList;
import java.util.List;

public class SupplierOrder {
	private List<SupplierProduct> products;

	public SupplierOrder() {
		products = new ArrayList<SupplierProduct>();
	}
	
	public SupplierOrder(List<SupplierProduct> products) {
		super();
		this.products = products;
	}

	public List<SupplierProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SupplierProduct> products) {
		this.products = products;
	}
	
	public void addProduct(SupplierProduct product) {
		this.products.add(product);
	}
}

package com.classes;

import java.util.List;

import javax.persistence.OneToMany;

import com.utils.DeliveryProduct;

public class DeliveryOrder {
	private String destin_address;
	private List<DeliveryProduct> products;
	
	public String getDestin_address() {
		return destin_address;
	}
	public void setDestin_address(String destin_address) {
		this.destin_address = destin_address;
	}
	public List<DeliveryProduct> getProducts() {
		return products;
	}
	public void setProducts(List<DeliveryProduct> products) {
		this.products = products;
	}

	
	
}

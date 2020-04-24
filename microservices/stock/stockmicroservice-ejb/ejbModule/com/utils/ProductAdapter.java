package com.utils;

import com.entities.Product;
import com.entities.ProductFromDrugstore;

public class ProductAdapter {
	private int id;

	private String description;

	private String keywords;

	private String name;

	private double price;
	
	private String type;
	
	private int amount;
	
	public ProductAdapter(Product product) {
		this.setId(product.getId());
		this.description=product.getDescription();
		this.keywords=product.getKeywords();
		this.name=product.getName();
		this.price=product.getPrice();
		this.setType("inventary");
		this.setAmount(product.getAmount());
	}
	
	public ProductAdapter(ProductFromDrugstore product) {
		this.setId(product.getId());
		this.description=product.getDescription();
		this.keywords=product.getKeywords();
		this.name=product.getName();
		this.price=product.getPrice();
		this.setType("drugstore");
		this.setAmount(0);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}

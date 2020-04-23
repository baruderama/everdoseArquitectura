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
	
	public ProductAdapter(Product product) {
		this.id=product.getId();
		this.description=product.getDescription();
		this.keywords=product.getKeywords();
		this.name=product.getName();
		this.price=product.getPrice();
		this.type="inventary";
	}
	
	public ProductAdapter(ProductFromDrugstore product) {
		this.id=product.getId();
		this.description=product.getDescription();
		this.keywords=product.getKeywords();
		this.name=product.getName();
		this.price=product.getPrice();
		this.type="drugstore";
	}
	
}

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
	
	private String image;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static final String INVENTARY="inventary";
	public static final String DRUGSTORE="drugstore";
	
	public ProductAdapter(Product product) {
		this.setId(product.getId());
		this.setDescription(product.getDescription());
		this.keywords=product.getKeywords();
		this.setName(product.getName());
		this.setPrice(product.getPrice());
		this.setType(INVENTARY);
		this.setAmount(product.getAmount());
		this.setImage(product.getImage());
	}
	
	public ProductAdapter(ProductFromDrugstore product) {
		this.setId(product.getId());
		this.setDescription(product.getDescription());
		this.keywords=product.getKeywords();
		this.setName(product.getName());
		this.setPrice(product.getPrice());
		this.setType(DRUGSTORE);
		this.setAmount(0);
		this.setImage(product.getImage());
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}

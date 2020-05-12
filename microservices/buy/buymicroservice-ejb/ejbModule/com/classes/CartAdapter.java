package com.classes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter {

	private int id;
	private Timestamp date;
	private String username;
	private List<CartProductAdapter> products;
	
	public CartAdapter() {
		this.products = new ArrayList<CartProductAdapter>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<CartProductAdapter> getProducts() {
		return products;
	}
	public void setProducts(List<CartProductAdapter> products) {
		this.products = products;
	}
	
	public void addProduct(CartProductAdapter product) {
		this.products.add(product);
	}
	
}

package com.classes;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Car database table.
 * 
 */
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	
	private Timestamp date;
	private String username;

	private List<CartProduct> products;

	public Car() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public List<CartProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CartProduct> products) {
		this.products = products;
	}

	public CartProduct addCartProduct(CartProduct CartProduct) {
		getProducts().add(CartProduct);
		CartProduct.setCar(this);

		return CartProduct;
	}

	public CartProduct removeCartProduct(CartProduct CartProduct) {
		getProducts().remove(CartProduct);
		CartProduct.setCar(null);

		return CartProduct;
	}
}
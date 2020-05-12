package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productadapter database table.
 * 
 */
@Entity
@NamedQuery(name="Productadapter.findAll", query="SELECT p FROM Productadapter p")
public class Productadapter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idproductcart;

	private int amount;

	private String description;

	private String image;

	private String keywords;

	private String name;

	private double price;

	private String type;

	//bi-directional many-to-one association to Cart
	@ManyToOne
	@JoinColumn(name="fk_idcart")
	private Cart cart;

	public Productadapter() {
	}

	public int getIdproductcart() {
		return this.idproductcart;
	}

	public void setIdproductcart(int idproductcart) {
		this.idproductcart = idproductcart;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
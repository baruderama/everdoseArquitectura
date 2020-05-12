package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cart database table.
 * 
 */
@Entity
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcart;

	private String date;

	private int idUser;

	//bi-directional many-to-one association to Productadapter
	@OneToMany(mappedBy="cart")
	private List<Productadapter> productadapters;

	public Cart() {
	}

	public int getIdcart() {
		return this.idcart;
	}

	public void setIdcart(int idcart) {
		this.idcart = idcart;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<Productadapter> getProductadapters() {
		return this.productadapters;
	}

	public void setProductadapters(List<Productadapter> productadapters) {
		this.productadapters = productadapters;
	}

	public Productadapter addProductadapter(Productadapter productadapter) {
		getProductadapters().add(productadapter);
		productadapter.setCart(this);

		return productadapter;
	}

	public Productadapter removeProductadapter(Productadapter productadapter) {
		getProductadapters().remove(productadapter);
		productadapter.setCart(null);

		return productadapter;
	}

}
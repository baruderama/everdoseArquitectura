package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the car database table.
 * 
 */
@Entity
@NamedQuery(name="Car.findAll", query="SELECT c FROM Car c")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcar;

	private int amount;

	private String carProductName;

	private String date;

	private int idProduct;

	private float price;

	private int userId;

	public Car() {
	}

	public int getIdcar() {
		return this.idcar;
	}

	public void setIdcar(int idcar) {
		this.idcar = idcar;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCarProductName() {
		return this.carProductName;
	}

	public void setCarProductName(String carProductName) {
		this.carProductName = carProductName;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
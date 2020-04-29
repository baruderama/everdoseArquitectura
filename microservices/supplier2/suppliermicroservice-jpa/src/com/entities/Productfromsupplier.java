package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productfromsupplier database table.
 * 
 */
@Entity
@NamedQuery(name="Productfromsupplier.findAll", query="SELECT p FROM Productfromsupplier p")
public class Productfromsupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idproductfromsupplier;

	private String description;

	private String keywords;

	private String name;

	private float price;

	@Column(name="supplier_id")
	private int supplierId;

	public Productfromsupplier() {
	}

	public int getIdproductfromsupplier() {
		return this.idproductfromsupplier;
	}

	public void setIdproductfromsupplier(int idproductfromsupplier) {
		this.idproductfromsupplier = idproductfromsupplier;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
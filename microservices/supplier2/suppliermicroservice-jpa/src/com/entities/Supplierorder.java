package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the supplierorder database table.
 * 
 */
@Entity
@NamedQuery(name="Supplierorder.findAll", query="SELECT s FROM Supplierorder s")
public class Supplierorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idsupplierorder;

	private int amount;

	private int payed;

	@Column(name="product_id")
	private int productId;

	@Column(name="product_name")
	private String productName;

	@Column(name="product_price")
	private float productPrice;

	@Column(name="supplier_id")
	private int supplierId;

	public Supplierorder() {
	}

	public int getIdsupplierorder() {
		return this.idsupplierorder;
	}

	public void setIdsupplierorder(int idsupplierorder) {
		this.idsupplierorder = idsupplierorder;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPayed() {
		return this.payed;
	}

	public void setPayed(int payed) {
		this.payed = payed;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the supplier database table.
 * 
 */
@Entity
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idsupplier;

	private String address;

	private String email;

	private String phone;

	@Column(name="supplier_name")
	private String supplierName;

	private String uri;

	public Supplier() {
	}

	public int getIdsupplier() {
		return this.idsupplier;
	}

	public void setIdsupplier(int idsupplier) {
		this.idsupplier = idsupplier;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
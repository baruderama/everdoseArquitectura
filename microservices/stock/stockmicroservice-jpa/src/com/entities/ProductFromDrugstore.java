package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ProductFromDrugstore database table.
 * 
 */
@Entity
@NamedQuery(name="ProductFromDrugstore.findAll", query="SELECT p FROM ProductFromDrugstore p")
public class ProductFromDrugstore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTFROMDRUGSTORE_ID_GENERATOR", sequenceName="SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTFROMDRUGSTORE_ID_GENERATOR")
	private int id;

	private String description;

	private String keywords;

	private String name;

	private float price;

	//bi-directional many-to-one association to Drugstore
	@ManyToOne
	@JoinColumn(name="drusgstore_id")
	private Drugstore drugstore;

	public ProductFromDrugstore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Drugstore getDrugstore() {
		return this.drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

}
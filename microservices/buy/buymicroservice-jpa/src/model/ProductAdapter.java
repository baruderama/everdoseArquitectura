package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ProductAdapter database table.
 * 
 */
@Entity
@NamedQuery(name="ProductAdapter.findAll", query="SELECT p FROM ProductAdapter p")
public class ProductAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID_internal;

	private int amount;

	private String description;

	private int id;

	private String image;

	private String keywords;

	private String name;

	private float price;

	private String type;

	//bi-directional many-to-one association to Car
	@ManyToOne(fetch=FetchType.LAZY)
	private Car car;

	public ProductAdapter() {
	}
	


	public int getID_internal() {
		return this.ID_internal;
	}

	public void setID_internal(int ID_internal) {
		this.ID_internal = ID_internal;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
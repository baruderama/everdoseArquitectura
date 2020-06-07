package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("stockmicroservice-jpa");
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String location;
	private String name;
	private String image;
	private String description;
	private String keywords;
	private float price;
	private int threshold;
	private int amount;
	
	public Product() {
		super();
	}

	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			if(Product.getProduct(this.id)==null)
				em.persist(this);
			et.commit();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
		}
		finally {
			em.close();
		}
		return succesfulltransaction;
	}

	public boolean removeProduct() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			Product product = em.find( Product.class , this.id);
			em.remove(product);
			et.commit();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
		}
		finally {
			em.close();
		}
		return succesfulltransaction;
	}

	public static List<Product> getProducts() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Product c WHERE c.id IS NOT NULL";
		TypedQuery<Product> tq = em.createQuery(query,  Product.class);
		List<Product> products = null;
		try {
			products = tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return products;
	}
	
	public static Product getProduct( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Product product = null;
		try {

			et = em.getTransaction();
			et.begin();
			product = em.find( Product.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return product;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public static boolean UpdateProductById(int id, String name2, String description2, String location2, String image2,
			Float price2, Integer threshold2, Integer amount2, String keyword) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		try {
			Product product=em.find(Product.class, id);
			em.getTransaction().begin();
			if(amount2!=null)product.setAmount(amount2);
			if(description2!=null)product.setDescription(description2);
			if(image2!=null)product.setImage(image2);
			if(keyword!=null)product.setKeywords(keyword);
			if(location2!=null)product.setLocation(location2);
			if(name2!=null)product.setName(name2);
			if(price2!=null)product.setPrice(price2);
			if(threshold2!=null)product.setThreshold(threshold2);
			em.getTransaction().commit();
			return true;
		}catch(Exception ignore) {
			
		}
		
		return false;
	}
   
}

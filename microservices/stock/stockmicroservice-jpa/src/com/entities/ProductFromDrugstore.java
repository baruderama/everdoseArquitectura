package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;


/**
 * The persistent class for the ProductFromDrugstore database table.
 * 
 */
@Entity
@NamedQuery(name="ProductFromDrugstore.findAll", query="SELECT p FROM ProductFromDrugstore p")
public class ProductFromDrugstore implements Serializable {
	private static final long serialVersionUID = 1L;
	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("stockmicroservice-jpa");

	@Id
	@Expose private int id;

	@Expose private String description;

	@Expose private String keywords;

	@Expose private String name;

	@Expose private float price;

	//bi-directional many-to-one association to Drugstore
	@ManyToOne
	@JoinColumn(name="drusgstore_id")
	private Drugstore drugstore;

	public ProductFromDrugstore() {
		super();
	}

	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			if(ProductFromDrugstore.getProduct(this.id)==null)
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
	
	public static ProductFromDrugstore getProduct( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		ProductFromDrugstore product = null;
		try {

			et = em.getTransaction();
			et.begin();
			product = em.find( ProductFromDrugstore.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return product;
	}
	
	public static List<ProductFromDrugstore> getProducts() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT p FROM ProductFromDrugstore p WHERE p.id IS NOT NULL";
		TypedQuery<ProductFromDrugstore> tq = em.createQuery(query, ProductFromDrugstore.class);
		List<ProductFromDrugstore> products = null;
		try {
			products = tq.getResultList();
			products.forEach( product -> System.out.println("ProductFromDrugstore") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return products;
	}

	public boolean removeProduct() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			ProductFromDrugstore product = em.find( ProductFromDrugstore.class , this.id);
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

	public static boolean UpdateProductById(int id2, String name2, String description2, Float price2,
			String keywords2) {
	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	try {
		ProductFromDrugstore product=em.find(ProductFromDrugstore.class, id2);
		em.getTransaction().begin();
		if(description2!=null)product.setDescription(description2);
		if(keywords2!=null)product.setKeywords(keywords2);
		if(name2!=null)product.setName(name2);
		if(price2!=null)product.setPrice(price2);
		em.getTransaction().commit();
		return true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	
		return false;
	}

}
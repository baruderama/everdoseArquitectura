package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductFromDrugstore
 *
 */
@Entity

public class ProductFromDrugstore implements Serializable {

	
	private static final long serialVersionUID = 1L;
	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("suppliermicroservice-jpa");
	
	@Id
	private int id;
	private int drugstore_id;
	private String name;
	private String description;
	private String keywords;
	private float price;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProductFromDrugstore() {
		super();
	}
	
	public int getDrugstore_id() {
		return drugstore_id;
	}

	public void setDrugstore_id(int drugstore_id) {
		this.drugstore_id = drugstore_id;
	}
	
	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			ProductFromDrugstore product = new ProductFromDrugstore();
			product.setName(name);
			product.setDrugstore_id(drugstore_id);
			product.setDescription(description);
			product.setKeywords(keywords);
			product.setPrice(price);
			em.persist(product);
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

	public boolean removeProductFromDrugstore() {
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

	public static List<ProductFromDrugstore> getProductsFromDrugstores() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM ProductFromDrugstore c WHERE c.id IS NOT NULL";
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
	
	public static ProductFromDrugstore getProductFromDrugstore( int id) {

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
   
}

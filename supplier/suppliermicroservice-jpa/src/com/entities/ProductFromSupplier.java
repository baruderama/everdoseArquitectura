package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductFromSupplierFromSupplier
 *
 */
@Entity
public class ProductFromSupplier implements Serializable {

	
	private static final long serialVersionUID = 1L;
	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("suppliermicroservice-jpa");
	
	@Id
	private int id;
	private int supplier_id;
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
	
	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	
	public ProductFromSupplier() {
		super();
	}
   
	public boolean save() {
		System.out.println("Saving...");
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			et = em.getTransaction();
			et.begin();
			ProductFromSupplier product = new ProductFromSupplier();
			product.setName(name);
			product.setSupplier_id(supplier_id);
			product.setDescription(description);
			product.setKeywords(keywords);
			product.setPrice(price);
			em.persist(product);
			et.commit();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		finally {
			em.close();
		}
		return succesfulltransaction;
	}

	public boolean removeProductFromSupplier() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			ProductFromSupplier product = em.find( ProductFromSupplier.class , this.id);
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

	public static List<ProductFromSupplier> getProductsFromSuppliers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM ProductFromSupplier c WHERE c.id IS NOT NULL";
		TypedQuery<ProductFromSupplier> tq = em.createQuery(query, ProductFromSupplier.class);
		List<ProductFromSupplier> products = null;
		try {
			products = tq.getResultList();
			products.forEach( product -> System.out.println("ProductFromSupplier") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return products;
	}
	
	public static ProductFromSupplier getProductFromSupplier( int id, int supplier_id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		ProductFromSupplier product = null;
		try {

			et = em.getTransaction();
			et.begin();
			product = em.find( ProductFromSupplier.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return product;
	}
}

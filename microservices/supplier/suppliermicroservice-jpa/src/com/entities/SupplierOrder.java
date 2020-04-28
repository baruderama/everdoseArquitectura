package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SupplierOrder
 *
 */
@Entity

public class SupplierOrder implements Serializable {

	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("suppliermicroservice-jpa");
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private int supplier_id;
	private String product_name;
	private float product_price;
	private int amount;
	private boolean payed;
	
	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public float getProduct_price() {
		return product_price;
	}

	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}

	public SupplierOrder() {
		super();
	}
	
	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			SupplierOrder supplierOrder = new SupplierOrder();
			supplierOrder.setProduct_name(product_name);
			supplierOrder.setProduct_price(product_price);
			supplierOrder.setSupplier_id(supplier_id);
			supplierOrder.setAmount(amount);
			em.persist(supplierOrder);
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
	
	public boolean removeSupplierOrder() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			SupplierOrder supplierOrder = em.find( SupplierOrder.class , this.id);
			em.remove(supplierOrder);
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

	public static List<SupplierOrder> getSupplierOrders() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM SupplierOrder c WHERE c.id IS NOT NULL";
		TypedQuery<SupplierOrder> tq = em.createQuery(query,  SupplierOrder.class);
		List<SupplierOrder> supplierOrders = null;
		try {
			supplierOrders = tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return supplierOrders;
	}
	
	public static SupplierOrder getSupplierOrder( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		SupplierOrder supplierOrder = null;
		try {
			et = em.getTransaction();
			et.begin();
			supplierOrder = em.find( SupplierOrder.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return supplierOrder;
	}
   
}

package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DeliveryOrder
 *
 */
@Entity
public class DeliveryOrder implements Serializable {

	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("deliverymicroservice-jpa");
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String destin_address;

	@OneToMany(mappedBy="deliveryOrder")
	private List<DeliveryProduct> products;
	
	public DeliveryOrder() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestin_address() {
		return destin_address;
	}

	public void setDestin_address(String destin_address) {
		this.destin_address = destin_address;
	}

	public int save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			DeliveryOrder deliveryOrder = new DeliveryOrder();
			deliveryOrder.setDestin_address(destin_address);
			em.persist( deliveryOrder );
			em.flush();
			et.commit();
			this.id = deliveryOrder.getId();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		finally {
			em.close();
		}
		return id;
	}

	public boolean removeDeliveryOrder() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			DeliveryOrder deliveryOrder = em.find( DeliveryOrder.class , this.id);
			em.remove(deliveryOrder);
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

	public static List<DeliveryOrder> getDeliveryOrders() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM DeliveryOrder c WHERE c.id IS NOT NULL";
		TypedQuery<DeliveryOrder> tq = em.createQuery(query,  DeliveryOrder.class);
		List<DeliveryOrder> deliveryOrders = null;
		try {
			deliveryOrders = tq.getResultList();
			deliveryOrders.forEach( deliveryOrder -> System.out.println("DeliveryOrder") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return deliveryOrders;
	}
	
	public static DeliveryOrder getDeliveryOrder( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		DeliveryOrder deliveryOrder = null;
		try {

			et = em.getTransaction();
			et.begin();
			deliveryOrder = em.find( DeliveryOrder.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return deliveryOrder;
	}

	public List<DeliveryProduct> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<DeliveryProduct> products) {
		this.products = products;
	}
   
}

package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DeliveryDeliveryProduct
 *
 */
@Entity
public class DeliveryProduct implements Serializable {

	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("deliverymicroservice-jpa");
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private float price;
	private String origin_address;
	private int amount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deliveryOrder_id")
	private DeliveryOrder deliveryOrder;
	

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public DeliveryProduct() {
		super();
	}

	public DeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}
	
	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			em.persist(this);
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

	public boolean removeDeliveryProduct() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			DeliveryProduct deliveryProduct = em.find( DeliveryProduct.class , this.id);
			em.remove(deliveryProduct);
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

	public static List<DeliveryProduct> getDeliveryProducts() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM DeliveryProduct c WHERE c.id IS NOT NULL";
		TypedQuery<DeliveryProduct> tq = em.createQuery(query,  DeliveryProduct.class);
		List<DeliveryProduct> deliveryProducts = null;
		try {
			deliveryProducts = tq.getResultList();
			deliveryProducts.forEach( deliveryProduct -> System.out.println("DeliveryProduct") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return deliveryProducts;
	}
	
	public static DeliveryProduct getDeliveryProduct( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		DeliveryProduct deliveryProduct = null;
		try {

			et = em.getTransaction();
			et.begin();
			deliveryProduct = em.find(DeliveryProduct.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return deliveryProduct;
	}

	public String getOrigin_address() {
		return origin_address;
	}

	public void setOrigin_address(String origin_address) {
		this.origin_address = origin_address;
	}
   
}

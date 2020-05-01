package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Supplier
 *
 */
@Entity

public class Supplier implements Serializable {

	
static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("suppliermicroservice-jpa");
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String uri;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Supplier() {
		super();
	}
	
	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			Supplier supplier = new Supplier();
			supplier.setName(name);
			supplier.setEmail(email);
			supplier.setPhone(phone);
			supplier.setUri(uri);
			supplier.setAddress(address);
			em.persist(supplier);
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
	
	public boolean removeSupplier() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			Supplier supplier = em.find( Supplier.class , this.id);
			em.remove(supplier);
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

	public static List<Supplier> getSuppliers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Supplier c WHERE c.id IS NOT NULL";
		TypedQuery<Supplier> tq = em.createQuery(query,  Supplier.class);
		List<Supplier> suppliers = null;
		try {
			suppliers = tq.getResultList();
			suppliers.forEach( Supplier -> System.out.println("Supplier") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return suppliers;
	}
	
	public static Supplier getSupplier( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Supplier supplier = null;
		try {

			et = em.getTransaction();
			et.begin();
			supplier = em.find( Supplier.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return supplier;
	}
   
}

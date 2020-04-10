package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Drugstore
 *
 */
@Entity

public class Drugstore implements Serializable {

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

	public Drugstore() {
		super();
	}
	
	public boolean save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			Drugstore drugstore = new Drugstore();
			drugstore.setName(name);
			drugstore.setEmail(email);
			drugstore.setPhone(phone);
			drugstore.setUri(uri);
			drugstore.setAddress(address);
			em.persist(drugstore);
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
	
	public boolean removeDrugstore() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			Drugstore drugstore = em.find( Drugstore.class , this.id);
			em.remove(drugstore);
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

	public static List<Drugstore> getDrugstores() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Drugstore c WHERE c.id IS NOT NULL";
		TypedQuery<Drugstore> tq = em.createQuery(query, Drugstore.class);
		List<Drugstore> drugstores = null;
		try {
			drugstores = tq.getResultList();
			drugstores.forEach( Drugstore -> System.out.println("Drugstore") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return drugstores;
	}
	
	public static Drugstore getDrugstore( int id) {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Drugstore drugstore = null;
		try {
			et = em.getTransaction();
			et.begin();
			drugstore = em.find( Drugstore.class , id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return drugstore;
	}
   
}

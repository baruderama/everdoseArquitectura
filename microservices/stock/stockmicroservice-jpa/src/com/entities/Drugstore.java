package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.List;


/**
 * The persistent class for the Drugstore database table.
 * 
 */

@NamedQuery(name="Drugstore.findAll", query="SELECT d FROM Drugstore d")
@Entity
public class Drugstore implements Serializable {
	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("stockmicroservice-jpa");
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DRUGSTORE_ID_GENERATOR", sequenceName="SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRUGSTORE_ID_GENERATOR")
	@Expose private int id;

	@Expose private String address;

	@Expose private String email;

	@Expose private String name;

	@Expose private String phone;

	@Expose private String uri;

	//bi-directional many-to-one association to ProductFromDrugstore
	@OneToMany(mappedBy="drugstore")
	private List<ProductFromDrugstore> productFromDrugstores;

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
			if(Drugstore.getDrugstore(this.id)==null)
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<ProductFromDrugstore> getProductFromDrugstores() {
		return this.productFromDrugstores;
	}

	public void setProductFromDrugstores(List<ProductFromDrugstore> productFromDrugstores) {
		this.productFromDrugstores = productFromDrugstores;
	}

	public ProductFromDrugstore addProductFromDrugstore(ProductFromDrugstore productFromDrugstore) {
		getProductFromDrugstores().add(productFromDrugstore);
		productFromDrugstore.setDrugstore(this);

		return productFromDrugstore;
	}

	public ProductFromDrugstore removeProductFromDrugstore(ProductFromDrugstore productFromDrugstore) {
		getProductFromDrugstores().remove(productFromDrugstore);
		productFromDrugstore.setDrugstore(null);

		return productFromDrugstore;
	}
	
	public static List<Drugstore> getDrugstores() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT d FROM Drugstore d WHERE d.id IS NOT NULL";
		TypedQuery<Drugstore> tq = em.createQuery(query,  Drugstore.class);
		List<Drugstore> drugstore = null;
		try {
			drugstore = tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return drugstore;
	}
	
	public static boolean UpdateDrugstoreById(int id, String address, String email, String name, String phone, String uri) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		try {
			Drugstore drugstore=em.find(Drugstore.class, id);
			em.getTransaction().begin();
			if(address!=null)drugstore.setAddress(address);
			if(email!=null)drugstore.setEmail(email);
			if(name!=null)drugstore.setName(name);
			if(phone!=null)drugstore.setPhone(phone);
			if(uri!=null)drugstore.setUri(uri);
			em.getTransaction().commit();
			return true;
		}
		catch(Exception ignore) {
			
		}
		
		return false;
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
	
	public static boolean deleteById(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		try {
			Drugstore drugstore=em.find(Drugstore.class, id);
			em.remove(drugstore);
			return true;
		}catch(Exception ignore) {
			
		}
		return false;
	}

}
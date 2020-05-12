package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Car database table.
 * 
 */
@Entity
@NamedQuery(name="Car.findAll", query="SELECT c FROM Car c")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("buymicroservice-jpa");


	@Id
	private int id;

	private Timestamp date;

	private String username;

	//bi-directional many-to-one association to ProductAdapter
	@OneToMany(mappedBy="car")
	private List<ProductAdapter> productAdapters;

	public Car() {
	}
	
	public int save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			if(true) {
				em.persist( this );
			}
			
			em.flush();
			et.commit();
			
			
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ProductAdapter> getProductAdapters() {
		return this.productAdapters;
	}

	public void setProductAdapters(List<ProductAdapter> productAdapters) {
		this.productAdapters = productAdapters;
	}

	public ProductAdapter addProductAdapter(ProductAdapter productAdapter) {
		getProductAdapters().add(productAdapter);
		productAdapter.setCar(this);

		return productAdapter;
	}

	public ProductAdapter removeProductAdapter(ProductAdapter productAdapter) {
		getProductAdapters().remove(productAdapter);
		productAdapter.setCar(null);

		return productAdapter;
	}

}
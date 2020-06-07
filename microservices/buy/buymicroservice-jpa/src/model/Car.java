package model;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Car database table.
 * 
 */
@Entity
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("buymicroservice-jpa");

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Timestamp date;
	private String username;

	//bi-directional many-to-one association to CartProduct
	@OneToMany(mappedBy="car")
	private List<CartProduct> products;

	public Car() {
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


	public List<CartProduct> getProducts() {
		List<CartProduct> products = new ArrayList<CartProduct>();
		List<CartProduct> temp = CartProduct.getCartProducts();
		for (CartProduct cartProduct : temp) {
			if(cartProduct.getCar().getId() == this.id) {
				products.add(cartProduct);
			}
		}
		return products;
	}

	public void setProducts(List<CartProduct> products) {
		this.products = products;
	}

	public CartProduct addCartProduct(CartProduct CartProduct) {
		getProducts().add(CartProduct);
		CartProduct.setCar(this);

		return CartProduct;
	}

	public CartProduct removeCartProduct(CartProduct CartProduct) {
		getProducts().remove(CartProduct);
		CartProduct.setCar(null);

		return CartProduct;
	}

	public int save() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Car car= new Car();
			car.setUsername(this.username);
			car.setDate(this.date);
			em.persist( car );
			em.flush();
			et.commit();
			this.id = car.getId();	
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
	
	public static List<Car> getCars() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Car c WHERE c.id IS NOT NULL";
		TypedQuery<Car> tq = em.createQuery(query,  Car.class);
		List<Car> cars = null;
		try {
			cars = tq.getResultList();
			cars.forEach( deliveryProduct -> System.out.println("Car") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return cars;
	}
}
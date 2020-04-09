package com.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.entities.Product;

/**
 * Session Bean implementation class StockService
 */
@Stateless
@LocalBean
public class StockService implements StockServiceRemote, StockServiceLocal {

	EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("stockmicroservice-jpa");
	
    /**
     * Default constructor. 
     */
    public StockService() {
    	
    }

	@Override
	public List<Product> getProducts() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Product c WHERE c.id IS NOT NULL";
		TypedQuery<Product> tq = em.createQuery(query, Product.class);
		List<Product> products = null;
		try {
			products = tq.getResultList();
			products.forEach( product -> System.out.println("Product") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return products;
	}

	@Override
	public boolean addProduct( String name, String description, String location, String image, float price, int threshold, int amount ) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		boolean succesfulltransaction = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setAmount(amount);
			product.setPrice(price);
			product.setImage(image);
			product.setThreshold(threshold);
			product.setLocation(location);
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

	@Override
	public boolean removeProduct( int id ) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		boolean succesfulltransaction = false;
		try {
			
			et = em.getTransaction();
			et.begin();
			Product product = em.find( Product.class , id);
			em.remove(product);
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

	@Override
	public boolean checkRunningOut() {
		// TODO Auto-generated method stub
		return false;
	}

}

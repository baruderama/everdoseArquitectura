package com.beans;

import java.util.List;
import java.util.function.Supplier;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.entities.Car;

/**
 * Session Bean implementation class BuyServices
 */
@Stateless
@LocalBean
public class BuyServices implements BuyServicesRemote, BuyServicesLocal {
	
	@PersistenceContext(unitName="buymicroservice-jpa",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public BuyServices() {
        // TODO Auto-generated constructor stub
    }
    

	@Override
	public Car findCar(int id) {
		Car car=entityManager.find(Car.class, id);
	    return car;
	}

	@Override
	public String addCarOrder(String date, int userId,String product, float price, int idProduct, int amount) {
		Car newCar = new Car();
	    
	    newCar.setCarProductName(product);
	    newCar.setPrice(price);
	    newCar.setIdProduct(idProduct);
	    newCar.setAmount(amount);
	    newCar.setDate(date);
	    newCar.setUserId(userId);
	    Car car = entityManager.find(Car.class, newCar.getIdcar());
	    if (car == null) {
	    entityManager.persist(newCar);
	    return "insertado";
	    } else
	    return "existe";
	}

	@Override
	public String updateCar(int id,String date,int userId, String product, float price, int idProduct, int amount) {
		// TODO Auto-generated method stub
		Car car = entityManager.find(Car.class, id);
		car.setCarProductName(product);
		car.setPrice(price);
		car.setIdProduct(idProduct);
		car.setAmount(amount);
		car.setDate(date);
		car.setUserId(userId);
		return "update";
	}

	@Override
	public String deleteCar(int id) {
		// TODO Auto-generated method stub
		Car car = entityManager.find(Car.class, id);
		entityManager.remove(car);
		return "deleted";
	}

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
		
	}


	@Override
	public List<Car> verHistorial(int id) {
		// TODO Auto-generated method stub
		List<Car> carList=entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
		List<Car> newCarList = null;
		for(Car c: carList) {
			if(c.getIdcar()==id) {
				newCarList.add(c);
			}
		}
		
		return newCarList;
	}


	@Override
	public void Comprar(int id) {
		List<Car> carList=entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
		List<Car> newCarList = null;
		for(Car c: carList) {
			if(c.getIdcar()==id) {
				newCarList.add(c);
			}
		}
		
		/*
		 * Aquí debería conectarse con pagos
		 */
		
		
		// TODO Auto-generated method stub
		
	}

}

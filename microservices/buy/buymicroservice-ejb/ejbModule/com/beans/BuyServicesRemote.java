package com.beans;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Car;

@Remote
public interface BuyServicesRemote {
	
	/*
	 * CRUD Car
	 */
	public Car findCar(int id);
	public String addCarOrder(String date,int userId,String product, float price, int idProduct, int amount);
	public String updateCar(int id,String date,int userId,String product, float price, int idProduct, int amount );
	public String deleteCar(int id);
	public List<Car> getAllCars();
	public List<Car> verHistorial(int id);
	public void Comprar(int id);
}

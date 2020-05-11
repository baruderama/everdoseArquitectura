package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Car;

@Local
public interface BuyServicesLocal {
	public Car findCar(int id);
	public String addCarOrder(String date,int userId,String product, float price, int idProduct, int amount);
	public String updateCar(int id,String date,int userId,String product, float price, int idProduct, int amount );
	public String deleteCar(int id);
	public List<Car> getAllCars();
	public List<Car> verHistorial(int id);
	public void Comprar(int id);

}

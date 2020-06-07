package com.utils;

import com.entities.Drugstore;
import com.entities.ProductFromDrugstore;

public class DeliveryProduct {
	private String name;
	private String description;
	private double price;
	private String origin_address;
	private int amount;
	
	private static final String home_address="direccion del inventario";
	
	
	public DeliveryProduct(ProductAdapter pa) {
		this.name=pa.getName();
		this.description=pa.getDescription();
		this.price=pa.getPrice();
		this.amount=pa.getAmount();
		if(pa.getType().equals(ProductAdapter.INVENTARY)) {
			this.origin_address=home_address;
		}
		if(pa.getType().equals(ProductAdapter.DRUGSTORE)) {
			ProductFromDrugstore pfd=ProductFromDrugstore.getProduct(pa.getId());
			Drugstore d=pfd.getDrugstore();
			this.origin_address=d.getAddress();
		}
	}
}

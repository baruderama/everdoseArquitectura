package com.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import com.entities.ProductFromSupplier;
import com.entities.Supplier;
import com.entities.SupplierOrder;

@Local
public interface SupplierServiceLocal {
	public boolean addSupplier( String name, String address, String phone, String email, String uri);
	public boolean addDrugstore( String name, String address, String phone, String email, String uri);
	public boolean addSupplierfinancialInformation();
	public boolean addProductToSupplier( int supplier_id, String name, String keywords, String description, float price );
	public ProductFromSupplier orderFromSupplier( String name, String keywords, int amount);
	public HashMap< Integer, Float> paySuppliers();
}

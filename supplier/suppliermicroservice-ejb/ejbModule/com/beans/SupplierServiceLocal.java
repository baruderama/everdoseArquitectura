package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Supplier;

@Local
public interface SupplierServiceLocal {
	public boolean addSupplier( String name, String address, String phone, String email, String uri);
	public boolean addDrugstore( String name, String address, String phone, String email, String uri);
	public boolean addSupplierfinancialInformation();
	public boolean order( String name, String keywords, String amount);
	public List<Supplier> paySuppliers();
}

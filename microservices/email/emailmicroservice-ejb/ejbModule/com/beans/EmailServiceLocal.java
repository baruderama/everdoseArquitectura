package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.classes.SupplierProduct;

@Local
public interface EmailServiceLocal {
	public boolean supplyProduct( String email, String supplierName, List<SupplierProduct> products );
}

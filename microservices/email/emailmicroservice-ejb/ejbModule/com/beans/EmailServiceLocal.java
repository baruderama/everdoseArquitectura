package com.beans;

import javax.ejb.Local;

@Local
public interface EmailServiceLocal {
	public boolean supplyProduct( String email, String supplierName, String products );
}

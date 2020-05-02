package com.beans;

import java.util.List;

import javax.ejb.Remote;

import com.utils.ProductAdapter;

@Remote
public interface StockServiceRemote {
	public boolean consumeProducts(List<ProductAdapter> products,String destiny_address);
	
}

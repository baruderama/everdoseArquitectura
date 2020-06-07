package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.DeliveryProduct;

@Local
public interface DeliveryServiceLocal {
	public boolean deliver( String destinAddress, List<DeliveryProduct> products );
}

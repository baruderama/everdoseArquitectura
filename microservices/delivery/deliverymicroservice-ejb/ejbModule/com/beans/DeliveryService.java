package com.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.DeliveryOrder;
import com.entities.DeliveryProduct;

/**
 * Session Bean implementation class DeliveryService
 */
@Stateless
@LocalBean
public class DeliveryService implements DeliveryServiceRemote, DeliveryServiceLocal {

    /**
     * Default constructor. 
     */
    public DeliveryService() {
    	
    }

	@Override
	public boolean deliver( String originAddress, String destinAddress, List<DeliveryProduct> products) {
		
		// TODO: Comunicación con servicio externo 
		boolean deliveryCreated = true;
		if (deliveryCreated) {
			DeliveryOrder deliveryOrder = new DeliveryOrder();
			deliveryOrder.setOrigin_address(originAddress);
			deliveryOrder.setDestin_address(destinAddress);
			deliveryOrder.save();
			for (DeliveryProduct deliveryProduct : products) {
				deliveryProduct.setDeliveryOrder(deliveryOrder);
				deliveryProduct.save();
			}
			deliveryCreated = true;
		}
		
		return deliveryCreated;
	}

}
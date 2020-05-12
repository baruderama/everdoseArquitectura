package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;
import com.entities.Cart;
import com.utils.ProductAdapter;

@Local
public interface BuyServiceLocal {

	public boolean buy(List<ProductAdapter> products, String token, StripeToken stripeToken, DeliveryInfo deliveryInfo, FinancialInfo financialInfo,String date,int idUser );
	public Cart verHistorial(int id);
}

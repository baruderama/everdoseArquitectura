package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.classes.CartAdapter;
import com.classes.DeliveryInfo;
import com.classes.FinancialInfo;
import com.classes.StripeToken;

import model.Car;
import model.CartProduct;

@Local
public interface BuyServiceLocal {

	boolean buy(List<CartProduct> products, String token, StripeToken stripeToken, DeliveryInfo deliveryInfo,
			FinancialInfo financialInfo, String productsString);
	List<CartAdapter> getPurchases(String username);
}

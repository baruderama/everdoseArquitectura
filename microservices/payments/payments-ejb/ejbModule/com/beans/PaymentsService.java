package com.beans;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class PaymentsService
 */
@Stateless
@LocalBean
public class PaymentsService implements PaymentsServiceRemote, PaymentsServiceLocal {

    /**
     * Default constructor. 
     */
    public PaymentsService() {
    }

	@Override
    public boolean creditPayment(String token,int amount,String description) {
		System.out.println("Servico de pagos (externo)");
        Stripe.apiKey = "sk_test_wMKd3NzoEEIRcXB5WdLGP0uV00gWNpE8Xm";
        
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");
        params.put("source", token);
        params.put(
          "description",
          description
        );
        
        System.out.println(token);
        try {
            Charge charge = Charge.create(params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}

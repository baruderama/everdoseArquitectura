package com.beans;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

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
        // TODO Auto-generated constructor stub
    }

    public String creditPayment(String token) {
        Stripe.apiKey = "sk_test_wMKd3NzoEEIRcXB5WdLGP0uV00gWNpE8Xm";

        try {
            Charge charge = Charge.retrieve(token);
            charge = charge.capture();
            return "success";
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return "error";
    }
}

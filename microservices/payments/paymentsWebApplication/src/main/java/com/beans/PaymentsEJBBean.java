package com.beans;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Stateless(name = "PaymentsEJBEJB")
public class PaymentsEJBBean {
    public PaymentsEJBBean() {
    }

    public String creditPayment(String token) {
        Stripe.apiKey = "sk_test_wMKd3NzoEEIRcXB5WdLGP0uV00gWNpE8Xm";

        try {
            Charge charge =Charge.retrieve(token);
            charge = charge.capture();
            return "success";
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return "error";
    }
}

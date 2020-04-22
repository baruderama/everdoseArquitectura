package com.beans;

import javax.ejb.Stateless;

@Stateless(name = "PaymentsEJBEJB")
public class PaymentsEJBBean {
    public PaymentsEJBBean() {
    }

    public String creditPayment(String name, String cardNumber, String cvv, String dateThru,double Value) {
        return "payment:"+cardNumber+":"+Value;
    }
}

package com.beans;

import javax.ejb.Remote;

@Remote
public interface PaymentsServiceRemote {

	boolean creditPayment(String token, int amount, String description);
}

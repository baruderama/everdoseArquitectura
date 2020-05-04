package com.beans;

import javax.ejb.Remote;

@Remote
public interface PaymentsServiceRemote {
	public boolean creditPayment(String token);
}

package com.beans;

import javax.ejb.Local;

@Local
public interface BuyServiceLocal {

	public boolean buy(String request, String token);
}

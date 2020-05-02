package com.classes;

public class Card {
	
	private String id;
	private String object;
	private String last4;
	private String brand;
	private int exp_month;
	private int exp_year;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getLast_4() {
		return last4;
	}
	public void setLast_4(String last_4) {
		this.last4 = last_4;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getExp_month() {
		return exp_month;
	}
	public void setExp_month(int exp_month) {
		this.exp_month = exp_month;
	}
	public int getExp_year() {
		return exp_year;
	}
	public void setExp_year(int exp_year) {
		this.exp_year = exp_year;
	}
	
}

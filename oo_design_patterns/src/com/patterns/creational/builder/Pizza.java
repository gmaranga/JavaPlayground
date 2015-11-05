package com.patterns.creational.builder;

public class Pizza {

	/** Porduct **/
	
	private String dough = "";
	private String sauce = "";
	private String topping = "";
	public void setDough(String dough) {
		this.dough = dough;
	}
	public void setSauce(String sauce) {
		this.sauce = sauce;
	}
	public String getDough() {
		return dough;
	}
	public String getSauce() {
		return sauce;
	}
	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}

	
	
}

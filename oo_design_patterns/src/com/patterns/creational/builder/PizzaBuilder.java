package com.patterns.creational.builder;

abstract public class PizzaBuilder {

	/** Abstract Builder **/
	
	protected Pizza pizza;
	
	public Pizza getPizza(){
		return pizza;
	}
	
	public void createPizzaProduct(){
		pizza = new Pizza();
	}
	
	public abstract void buildDougth();
	public abstract void buildSauce();
	public abstract void buildTopping();
	
}

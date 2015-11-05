package com.patterns.creational.builder;


public class Cook {

	
	/** Director **/
	
	
	private PizzaBuilder pizzaBuilder;

	public PizzaBuilder getPizzaBuilder() {
		return pizzaBuilder;
	}

	public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
		this.pizzaBuilder = pizzaBuilder;
	}
	
	public Pizza getPizza(){
		return pizzaBuilder.getPizza();
	}
	
	public void constructPizza(){
	
		pizzaBuilder.createPizzaProduct();
		pizzaBuilder.buildDougth();
		pizzaBuilder.buildSauce();
		pizzaBuilder.buildTopping();
	}	
	
	
}

package com.patterns.creational.builder;

public class SpicyPizzaBuilder extends PizzaBuilder{

	/** ConcreteBuilder **/
	
	@Override
	public void buildDougth() {
		pizza.setDough("pan baked");
		
	}

	@Override
	public void buildSauce() {
		pizza.setSauce("hot");
		
	}

	@Override
	public void buildTopping() {
		pizza.setTopping("pepperoni+salami");
		
	}
			
}

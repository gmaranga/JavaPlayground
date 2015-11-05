package com.patterns.creational.builder;

public class BuilderExample {

	/** The intention is to abstract steps of construction of objects
	 *  so that different implementations of these steps can construct
	 *   different representations of objects. **/	
	
	/** A given type of pizza being constructed. */
	
	public static void main(String[] args) {
		
		//Director
		Cook cook = new Cook();
		PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
		PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();
		
		cook.setPizzaBuilder(hawaiianPizzaBuilder);
		cook.constructPizza();
		
		Pizza hawaiian = cook.getPizza();
		 
		cook.setPizzaBuilder(spicyPizzaBuilder);
		cook.constructPizza();
		
		Pizza spicy = cook.getPizza();

		
	}

}

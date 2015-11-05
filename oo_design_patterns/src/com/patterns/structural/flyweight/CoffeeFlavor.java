package com.patterns.structural.flyweight;

public class CoffeeFlavor implements CoffeeOrder {

	// ConcreteFlyweight object that creates ConcreteFlyweight 
	
	private String flavor;
	 
    public CoffeeFlavor(String newFlavor) {
        this.flavor = newFlavor;
    }
 
    public String getFlavor() {
        return this.flavor;
    }
	
	@Override
	public void serveCoffee(CoffeeOrderContext context) {
		 System.out.println("Serving Coffee flavor " + flavor + " to table number " + context.getTable());
	}

}

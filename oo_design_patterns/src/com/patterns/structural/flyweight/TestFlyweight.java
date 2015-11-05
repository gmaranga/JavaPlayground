package com.patterns.structural.flyweight;


/**
 * In computer programming, flyweight is a software design pattern. 
 * A flyweight is an object that minimizes memory use by sharing as much data as possible with other similar objects;
 * it is a way to use objects in large numbers when a simple repeated representation 
 * would use an unacceptable amount of memory. 
 * Often some parts of the object state can be shared, and it is common practice to hold them in 
 * external data structures and pass them to the flyweight objects temporarily when they are used.
 * @author gustavo.m.maranga
 *
 */
public class TestFlyweight {

	
		/** The flavors ordered. */
	   private static CoffeeFlavor[] flavors = new CoffeeFlavor[100];
	   /** The tables for the orders. */
	   private static CoffeeOrderContext[] tables = new CoffeeOrderContext[100];
	   private static int ordersMade = 0;
	   private static CoffeeFlavorFactory flavorFactory;
	   
	   
	   public static void takeOrders(String flavorIn, int table) {
	       flavors[ordersMade] = flavorFactory.getCoffeeFlavor(flavorIn);
	       tables[ordersMade++] = new CoffeeOrderContext(table);
	   }
	 
	   public static void main(String[] args) {
	       flavorFactory = new CoffeeFlavorFactory();
	 
	       takeOrders("Cappuccino", 2);
	       takeOrders("Cappuccino", 2);
	       takeOrders("Frappe", 1);
	       takeOrders("Frappe", 1);
	       takeOrders("Xpresso", 1);
	       takeOrders("Frappe", 897);
	       takeOrders("Cappuccino", 97);
	       takeOrders("Cappuccino", 97);
	       takeOrders("Frappe", 3);
	       takeOrders("Xpresso", 3);
	       takeOrders("Cappuccino", 3);
	       takeOrders("Xpresso", 96);
	       takeOrders("Frappe", 552);
	       takeOrders("Cappuccino", 121);
	       takeOrders("Xpresso", 121);
	 
	       for (int i = 0; i < ordersMade; ++i) {
	           flavors[i].serveCoffee(tables[i]);
	       }
	       System.out.println(" ");
	       System.out.println("total CoffeeFlavor objects made: " +  flavorFactory.getTotalCoffeeFlavorsMade());
	   }
}

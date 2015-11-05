package com.patterns.creational.abstractfactory;

public class GardenMaker {

	/**
	 * Provide an interface for creating families of related 
	 * or dependent objects without specifying their concrete classes.
	 */
	
	private Garden gd;
	
	public Garden getGarden(String gtype){
		
		gd = new VegieGarden();
		if(gtype.equals("Perennial"))
			gd = new PerennialGarden();
		if(gtype.equals("Annual"))
			gd = new AnnualGarden();
		return gd;
		
	}
	
}

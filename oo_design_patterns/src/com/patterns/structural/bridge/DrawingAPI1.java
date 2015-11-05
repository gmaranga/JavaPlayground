package com.patterns.structural.bridge;

public class DrawingAPI1 implements DrawingAPI {

	/** "ConcreteImplementor" 1/2 
	 * Implementor
    	defines the interface for implementation classes
	 * */

	
	@Override
	public void drawCircle(double x, double y, double radius) {
		System.out.printf("API1.circle at %f:%f radius %f\n", x, y, radius);
	}

}

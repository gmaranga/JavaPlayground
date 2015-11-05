package com.patterns.structural.bridge;

public class BridgePattern {
	
	/** The bridge pattern is a design pattern 
	 * 	used in software engineering which is meant to
	 *  decouple an abstraction from its implementation 
	 *  so that the two can vary independently.
	 *  The bridge pattern is useful when both the class 
	 *  as well as what it does vary often.
	 *  The class itself can be thought of as the implementation
	 *  and what the class can do as the abstraction.
	 *  The bridge pattern can also be thought of as two layers of abstraction.
	 */
	
	public static void main(String[] args) {
		Shape[]shapes = new Shape[]{new CircleShape(1, 2, 3, new DrawingAPI1()),
				new CircleShape(5, 7, 11, new DrawingAPI2())};

		for(Shape shape: shapes){
			shape.resizeByPercentage(2.5);
			shape.draw();
		}
	}
	
	
}

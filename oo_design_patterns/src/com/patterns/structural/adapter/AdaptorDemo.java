package com.patterns.structural.adapter;

public class AdaptorDemo {

	/**
	 * The adapter pattern is useful in situations where an 
	 already existing class provides some or all of the services 
	 you need but does not use the interface you need.**/


	public static void main(String[] args) {
		
		Shape[] shapes = {new Line(), new Rectangle()};
		
		int x1 = 10, y1 = 20;
		int x2 = 30, y2 = 60;
		
		for(Shape shape : shapes){
			shape.draw(x1, y1, x2, y2);
		}

	}

}

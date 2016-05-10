package com.javawellgrounded.java8features;

public interface Formula {

	double calculate(int a);
	
	//Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the default keyword.
	//Also called Extension Methods
	default double sqrt(int a){
		return Math.sqrt(a);
	}
	
}

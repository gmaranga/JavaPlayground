package com.patterns.behavioral.strategy;

public class StrategyExample {

	/**
	 * Define a family of algorithms, encapsulate each one, and make them interchangeable.
	 *  Strategy lets the algorithm vary independently from clients that use it.
	 */
	public static void main(String[] args) {
		
		Context context;
		
		context = new Context(new ConcreteStrategyAdd());
		int resultA = context.executeStrategy(3,4);
		System.out.println("ConcreteStrategyAdd: " + resultA);
		
		context = new Context(new ConcreteStrategySubtract());
        int resultB = context.executeStrategy(3,4);
		System.out.println("ConcreteStrategySubtract: " + resultB);
		
		context = new Context(new ConcreteStrategyMultiply());
        int resultC = context.executeStrategy(3,4);
        System.out.println("ConcreteStrategyMultiply: " + resultC);
        
	}

}

package com.patterns.behavioral.strategy;

/**
 * Implements the algorithm using the strategy interface
 * @author gustavo.m.maranga
 *
 */
public class ConcreteStrategySubtract implements Strategy {

	@Override
	public int execute(int a, int b) {
		 System.out.println("Called ConcreteStrategySubtract's execute()");
	        return a - b;  // Do a subtraction with a and b
	}

}

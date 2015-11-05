package com.patterns.behavioral.strategy;

/**
 * Implements the algorithm using the strategy interface
 * @author gustavo.m.maranga
 *
 */
public class ConcreteStrategyAdd implements Strategy {

	@Override
	public int execute(int a, int b) {
		 System.out.println("Called ConcreteStrategyAdd's execute()");
	     return a + b;  // Do an addition with a and b
	}

}

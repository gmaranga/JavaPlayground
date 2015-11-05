package com.patterns.behavioral.strategy;

/**
 * Implements the algorithm using the strategy interface
 * @author gustavo.m.maranga
 *
 */
public class ConcreteStrategyMultiply implements Strategy {

	@Override
	public int execute(int a, int b) {
		System.out.println("Called ConcreteStrategyMultiply's execute()");
        return a * b;   // Do a multiplication with a and b
	}

}

package com.patterns.behavioral.state;

public class StateB implements State {

	private int count = 0;
	@Override
	public void writeName(StateContext stateContext, String name) {
		System.out.println(name.toUpperCase());
		// change state after StateB's writeName() gets invoked twice
        if(++count>1) { 
                stateContext.setState(new StateA()); 
        }
	}

}

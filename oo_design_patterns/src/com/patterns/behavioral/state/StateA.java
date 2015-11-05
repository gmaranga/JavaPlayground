package com.patterns.behavioral.state;

public class StateA implements State {

	@Override
	public void writeName(StateContext stateContext, String name) {
		
		System.out.println(name.toLowerCase());
		stateContext.setState(new StateB());
		
	}

}

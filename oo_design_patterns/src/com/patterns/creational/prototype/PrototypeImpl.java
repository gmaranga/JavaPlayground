package com.patterns.creational.prototype;

public class PrototypeImpl extends PrototypeFactory {

	//Concrete Prototypes to clone
	
	int x;
	
	public PrototypeImpl(int x) {
		this.x = x;
	}

	@Override
	void prototypeFactory(int x) {
		this.x = x;
	}

	@Override
	void printValue() {
		System.out.println("Value: " + x);
	}

}

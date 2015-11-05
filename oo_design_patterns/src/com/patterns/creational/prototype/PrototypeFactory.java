package com.patterns.creational.prototype;

public abstract class PrototypeFactory implements Cloneable{
	
	//Prototype Class
	
	public Object clone() throws CloneNotSupportedException{
		
		//call Object.clone()
		PrototypeFactory copy = (PrototypeFactory) super.clone();
		//In an actual implementation of this pattern you might now change references to
        //the expensive to produce parts from the copies that are held inside the prototype.
		return copy;
	}
	
	abstract void prototypeFactory(int x);
	abstract void printValue();
	
}

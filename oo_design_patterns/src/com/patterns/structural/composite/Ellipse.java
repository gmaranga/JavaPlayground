package com.patterns.structural.composite;

public class Ellipse implements Graphic {

	/**
	 * Leaf

    * represents leaf objects in the composition .
    * implements all Component methods

	 */
	
	//Prints the graphic
	@Override
	public void print() {
		System.out.println("Ellipse");
	}

}

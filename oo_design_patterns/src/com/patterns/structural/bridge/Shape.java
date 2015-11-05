package com.patterns.structural.bridge;

public interface Shape {

	/** Abstraction
    defines the abstract interface
    maintains the Implementor reference **/

	
	 public void draw();                                            // low-level
	 public void resizeByPercentage(double pct);     // high-level

}

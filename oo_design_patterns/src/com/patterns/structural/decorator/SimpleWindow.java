package com.patterns.structural.decorator;

public class SimpleWindow implements Window {
	
	// implementation of a simple Window without any scrollbars
	@Override
	public void draw() {
		// draw window
	}

	@Override
	public String getDescription() {
		return "simple window";
	}

}

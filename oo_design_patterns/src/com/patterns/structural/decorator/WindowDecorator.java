package com.patterns.structural.decorator;

public abstract class WindowDecorator implements Window {

	// abstract decorator class - note that it implements Window

	protected Window decoratedWindow; // the Window being decorated

	public WindowDecorator(Window decoratedWindow) {
		this.decoratedWindow = decoratedWindow;
	}

	@Override
	public void draw() {
		decoratedWindow.draw(); //delegation
	}

	@Override
	public String getDescription() {
		return decoratedWindow.getDescription(); //delegation
	}

}

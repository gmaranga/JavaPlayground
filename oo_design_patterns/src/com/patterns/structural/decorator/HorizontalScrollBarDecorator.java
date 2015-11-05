package com.patterns.structural.decorator;

public class HorizontalScrollBarDecorator extends WindowDecorator {

	// the second concrete decorator which adds horizontal scrollbar
	// functionality
	public HorizontalScrollBarDecorator(Window decoratedWindow) {
		super(decoratedWindow);
	}

	@Override
	public void draw() {
		decoratedWindow.draw();
		drawHorizontalScrollBar();
	}

	private void drawHorizontalScrollBar() {
		// draw the horizontal scrollbar
	}

	@Override
	public String getDescription() {
		return decoratedWindow.getDescription()	+ ", including horizontal scrollbars";
	}
}

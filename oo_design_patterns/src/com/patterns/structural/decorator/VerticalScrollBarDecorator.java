package com.patterns.structural.decorator;

public class VerticalScrollBarDecorator extends WindowDecorator {

	// the first concrete decorator which adds vertical scrollbar functionality
	public VerticalScrollBarDecorator(Window decoratedWindow) {
		super(decoratedWindow);
	}

	@Override
	public void draw() {
		decoratedWindow.draw();
		drawVerticalScrollBar();
	}

	private void drawVerticalScrollBar() {
		// draw the vertical scrollbar
	}

	@Override
	public String getDescription() {
		return decoratedWindow.getDescription()	+ ", including vertical scrollbars";
	}

}

package com.patterns.structural.decorator;

public class DecoratedWindowTest {

	/**
	 * The decorator pattern is a design pattern that allows behaviour to be added to an existing object dynamically.
	 * This pattern is designed so that multiple decorators can be stacked on top of each other, each time adding a new functionality to the overridden method(s).
	 * The decorator pattern is an alternative to subclassing. Subclassing adds behavior at compile time, and the change affects all instances of the original class;
	 *  decorating can provide new behavior at run-time for individual objects.
	 */
	public static void main(String[] args) {

		// create a decorated Window with horizontal and vertical scrollbars
		Window decoratedWindow = new HorizontalScrollBarDecorator(new VerticalScrollBarDecorator(new SimpleWindow()));
		// print the Window's description
		System.out.println(decoratedWindow.getDescription());

	}

}

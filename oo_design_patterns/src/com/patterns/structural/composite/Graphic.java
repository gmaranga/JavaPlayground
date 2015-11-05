package com.patterns.structural.composite;


/**
 * Component
 * is the abstraction for all components, including composite ones.
 * declares the interface for objects in the composition.
 * (optional) defines an interface for accessing a component's parent in
 *  the recursive structure, and implements it if that's appropriate.
 *
 */
public interface Graphic {

	public void print();
}

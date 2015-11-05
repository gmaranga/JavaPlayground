package com.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeGraphic implements Graphic {
	
	/** Composite
    * represents a composite Component (component having children).
    * implements methods to manipulate children.
    * implements all Component methods, generally by delegating them to its children.
    */
	
	
	//Collection of child graphics
	private List<Graphic> mChildGraphics = new ArrayList<Graphic>();
	
	//Prints the Graphic
	@Override
	public void print() {
		for (Graphic graphic : mChildGraphics) {
			graphic.print();
		}

	}
	
	//Adds the graphic to the composition.
    public void add(Graphic graphic) {
        mChildGraphics.add(graphic);
    }
 
    //Removes the graphic from the composition.
    public void remove(Graphic graphic) {
        mChildGraphics.remove(graphic);
    }


}

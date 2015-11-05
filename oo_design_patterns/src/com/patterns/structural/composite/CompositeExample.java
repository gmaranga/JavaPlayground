package com.patterns.structural.composite;

public class CompositeExample {

	/**
	 * When dealing with tree-structured data, programmers often have to discriminate
	 *  between a leaf-node and a branch. This makes code more complex, and therefore,
	 *  error prone. 
	 * The solution is an interface that allows treating complex and primitive objects
	 *  uniformly.
	 *  Composite can be used when clients should ignore the difference between compositions
	 *   of objects and individual objects.
	 */
	

    
    
    
	public static void main(String[] args) {

		 //Initialize four ellipses
	    Ellipse ellipse1 = new Ellipse();
	    Ellipse ellipse2 = new Ellipse();
	    Ellipse ellipse3 = new Ellipse();
	    Ellipse ellipse4 = new Ellipse();

	    //Initialize three composite graphics
	    CompositeGraphic graphic = new CompositeGraphic();
	    CompositeGraphic graphic1 = new CompositeGraphic();
	    CompositeGraphic graphic2 = new CompositeGraphic();
		
		//Composes the graphics
        graphic1.add(ellipse1);
        graphic1.add(ellipse2);
        graphic1.add(ellipse3);
 
        graphic2.add(ellipse4);
 
        graphic.add(graphic1);
        graphic.add(graphic2);

      //Prints the complete graphic (four times the string "Ellipse").
       graphic.print();

	}

}

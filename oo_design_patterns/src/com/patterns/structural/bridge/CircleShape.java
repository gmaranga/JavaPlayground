package com.patterns.structural.bridge;

public class CircleShape implements Shape {
	
	
	/**
	 * RefinedAbstraction
    	extends the interface defined by Abstraction
	 */
	private double x, y, radius;
	private DrawingAPI drawingAPI;
	
	public CircleShape(double x, double y, double radius, DrawingAPI drawingAPI) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.drawingAPI = drawingAPI;
	}
	
	// low-level i.e. Implementation specific
	@Override
	public void draw() {
		drawingAPI.drawCircle(x, y, radius);
	}

	@Override
	public void resizeByPercentage(double pct) {
		radius *=pct;
	}

	

}

package com.patterns.creational.abstractfactory;

public class VegieGarden extends Garden {

	@Override
	public Plant getBorder() {
		
		return new Plant("Peas");
	}

	@Override
	public Plant getCenter() {
		
		return new Plant("Corn");
	}

	@Override
	public Plant getShade() {

		return new Plant("Broccoli");
	}

}

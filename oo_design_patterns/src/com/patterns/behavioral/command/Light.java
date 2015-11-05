package com.patterns.behavioral.command;

/**
 * The receiver
 * @author gustavo.m.maranga
 *
 */
public class Light {

	
	public Light() {}
	
	public void turnOn(){
		System.out.println("The light is on");
	}
	
	public void turnOff(){
		System.out.println("The light is off");
	}
}

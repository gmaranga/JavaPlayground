package com.patterns.behavioral.command;

/**
 * Command Implementation for turning the light on
 * @author gustavo.m.maranga
 *
 */
public class FlipUpCommand implements Command {

	
	private Light light;
	
	
	 public FlipUpCommand(Light light) {
		 this.light = light;
	 }
	
	@Override
	public void execute() {
		this.light.turnOn();
	}

}

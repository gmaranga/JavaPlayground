package com.patterns.behavioral.command;

/**
 * Command Implementation for turning the light off
 * @author gustavo.m.maranga
 *
 */
public class FlipDownCommand implements Command {

	 private Light light;
	 
	   public FlipDownCommand(Light light) {
	        this.light = light;
	   }
	 
	   public void execute() {
	      light.turnOff();
	   }

}

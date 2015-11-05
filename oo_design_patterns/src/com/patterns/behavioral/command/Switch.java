package com.patterns.behavioral.command;


/**
 * The invoker
 * @author gustavo.m.maranga
 *
 */
public class Switch {

	private Command flipUpCommand;
	private Command flipDownCommand;
	
	public Switch(Command flipUpCommand, Command flipDownCommand){
		this.flipUpCommand = flipDownCommand;
		this.flipDownCommand = flipDownCommand;
	}
	
	
	public void flipUp(){
		flipUpCommand.execute();
	}
	
	public void flipDown(){
		flipDownCommand.execute();
	}
}

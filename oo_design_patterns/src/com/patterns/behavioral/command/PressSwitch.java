package com.patterns.behavioral.command;
/**
 * Client
 * @author gustavo.m.maranga
 *
 */
public class PressSwitch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Light light = new Light();
		Command switchUp = new FlipUpCommand(light);
		Command switchDown = new FlipDownCommand(light);
		
		// See criticism of this model above:
       // The switch itself should not be aware of lamp details (switchUp, switchDown) 
       // either directly or indirectly
       Switch s = new Switch(switchUp, switchDown);
 
       try {
           if (args[0].equalsIgnoreCase("ON")) {
                s.flipUp();
           } else if (args[0].equalsIgnoreCase("OFF")) {
               s.flipDown();
           } else {
               System.out.println("Argument \"ON\" or \"OFF\" is required.");
           }
       } catch (Exception e) {
           System.out.println("Arguments required.");
       }		

	}

}

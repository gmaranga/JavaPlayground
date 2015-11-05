package com.patterns.behavioral.chainofreposability;


/**
 *  In Object Oriented Design, the chain-of-responsibility pattern is a design pattern consisting of
 *  a source of command objects and a series of processing objects.
 *  Each processing object contains logic that defines the types of command objects that it can handle;
 *   the rest are passed to the next processing object in the chain. 
 * 
 * @author gustavo.m.maranga
 *
 */
public class ChainOfResponsibilityExample {

	 public static void main(String[] args) {
	        // Build the chain of responsibility
	        Logger logger, logger1,logger2;
	        logger = new StdoutLogger(Logger.DEBUG);
	        logger1 = logger.setNext(new EmailLogger(Logger.NOTICE));
	        logger2 = logger1.setNext(new StderrLogger(Logger.ERR));
	 
	        // Handled by StdoutLogger
	        logger.message("Entering function y.", Logger.DEBUG);
	 
	        // Handled by StdoutLogger and EmailLogger
	        logger.message("Step1 completed.", Logger.NOTICE);
	 
	        // Handled by all three loggers
	        logger.message("An error has occurred.", Logger.ERR);
	    }
	
}

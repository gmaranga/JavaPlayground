package com.patterns.behavioral.chainofreposability;

public class StderrLogger extends Logger {

	public StderrLogger(int mask) {
		this.mask = mask;
	}
	
	@Override
	protected void writeMessage(String msg) {
		System.err.println("Sending to stderr: " + msg);
	}

}

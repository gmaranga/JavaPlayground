package com.patterns.behavioral.chainofreposability;

public class StdoutLogger extends Logger {

	public StdoutLogger(int mask) {
		this.mask = mask;
	}

	@Override
	protected void writeMessage(String msg) {
		System.out.println("Writing to stdout: " + msg);
	}

}

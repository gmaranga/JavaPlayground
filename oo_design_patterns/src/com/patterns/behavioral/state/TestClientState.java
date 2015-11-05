package com.patterns.behavioral.state;

public class TestClientState {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StateContext sc = new StateContext(); 
	        sc.writeName("Monday"); 
	        sc.writeName("Tuesday"); 
	        sc.writeName("Wednesday"); 
	        sc.writeName("Thursday"); 
	        sc.writeName("Saturday"); 
	        sc.writeName("Sunday"); 

	}

}

package com.patterns.structural.proxy;


/**
 * In computer programming, the proxy pattern is a software design pattern.
 * A proxy, in its most general form, is a class functioning as an interface to something else.
 * The proxy could interface to anything: a network connection, a large object in memory, a file,
 * or some other resource that is expensive or impossible to duplicate.
 * A well-known example of the proxy pattern is a reference counting pointer object.
 * In situations where multiple copies of a complex object must exist, 
 * the proxy pattern can be adapted to incorporate the flyweight pattern in order to reduce the application's memory footprint. Typically, one instance of the complex object and multiple proxy objects are created, all of which contain a reference to the single original complex object. Any operations performed on the proxies are forwarded to the original object. 
 * Once all instances of the proxy are out of scope, the complex object's memory may be deallocated.
 *
 * @author gustavo.m.maranga
 *
 */

public class ProxyExample {

		/**
	    * Test method
	    */
	   public static void main(String[] args) {
	        final Image IMAGE1 = new ProxyImage("HiRes_10MB_Photo1");
	        final Image IMAGE2 = new ProxyImage("HiRes_10MB_Photo2");     
	 
	        IMAGE1.displayImage(); // loading necessary
	        IMAGE1.displayImage(); // loading unnecessary
	        IMAGE2.displayImage(); // loading necessary
	        IMAGE2.displayImage(); // loading unnecessary
	        IMAGE1.displayImage(); // loading unnecessary
	    }
	
}

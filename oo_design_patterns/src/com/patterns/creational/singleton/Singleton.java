package com.patterns.creational.singleton;

public class Singleton {
	 
	
	//Ensure a class has only one instance, and provide a global point of access to it.
    private static final Singleton INSTANCE = new Singleton();
 
    // Private constructor prevents instantiation from other classes
    private Singleton() {
    }
 
    public static Singleton getInstance() {
        return INSTANCE;
    }
 
}



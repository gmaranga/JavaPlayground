package com.patterns.creational.singleton;

public class ThreadSafeSingleton {

	
	// volatile is needed so that multiple threads can reconcile the instance
    // semantics for volatile changed in Java 5.
	private volatile static ThreadSafeSingleton threadSafeSingleton;
	
	private ThreadSafeSingleton(){}
	
	
    // synchronized keyword has been removed from here
	public static ThreadSafeSingleton getThreadSafeSingleton(){
		// needed because once there is singleton available no need to acquire
        // monitor again & again as it is costly
		if(threadSafeSingleton == null){
			synchronized (ThreadSafeSingleton.class) {
				// this is needed if two threads are waiting at the monitor at the
	            // time when singleton was getting instantiated
				if(threadSafeSingleton == null){
					threadSafeSingleton = new ThreadSafeSingleton();
				}
			}
		}
		return 	threadSafeSingleton;
		
	}
	
}

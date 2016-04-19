package com.javawellgrounded.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class STPEExample {

	/*
	 * ScheduledExecutorService is the backbone of the thread pool classes—it’s versatile and as a result
	 * is quite common. The STPE takes in work in the form of tasks and
	 * schedules them on a pool of threads.
	 */
	private static ScheduledExecutorService stpe1;
	private static BlockingQueue<WorkUnit<Integer>> abq = new ArrayBlockingQueue<>(1);

	public static void main(String[] args) {

		/*
		 * STPE is only one of a number of related executors that can be
		 * obtained very easily by using factory methods available on the
		 * Executors class in java.util.concurrent.
		 */
		stpe1 = Executors.newScheduledThreadPool(1);
		PrimeNumberGenerator png = new PrimeNumberGenerator(abq);
		stpe1.scheduleWithFixedDelay(png, 2, 2, TimeUnit.SECONDS);
		
		
		
	}
	
	

}

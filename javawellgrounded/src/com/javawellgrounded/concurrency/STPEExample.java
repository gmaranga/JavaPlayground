package com.javawellgrounded.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class STPEExample {

	/*
	 * ScheduledExecutorService is the backbone of the thread pool classes—it’s versatile and as a result
	 * is quite common. The STPE takes in work in the form of tasks and
	 * schedules them on a pool of threads.
	 */
	private static BlockingQueue<WorkUnit<Integer>> abq = new ArrayBlockingQueue<>(1);

	public static void main(String[] args) {

		/*
		 * STPE is only one of a number of related executors that can be
		 * obtained very easily by using factory methods available on the
		 * Executors class in java.util.concurrent.
		 */
		ScheduledExecutorService stpe1 = Executors.newScheduledThreadPool(1);
		PrimeNumberGenerator png = new PrimeNumberGenerator(abq);
		stpe1.scheduleWithFixedDelay(png, 2, 2, TimeUnit.SECONDS);
		
		ScheduledExecutorService stpe2 = Executors.newScheduledThreadPool(5);
		
		ScheduledExecutorService stpe3 = Executors.newSingleThreadScheduledExecutor();
		
		
		
		stpe3.scheduleWithFixedDelay(new Runnable() {
		
			List<Future<?>> hndls = new ArrayList<>();
			
			@Override
			public void run() {
				if(isProcessingFinished(hndls)){
					System.out.println("All hashes generated!");
					hndls = new ArrayList<>();
					for(int j = 0; j < 10; j++){
						hndls.add(stpe2.submit(new HashGenerator(abq)));
					}
				}else{
					System.out.println("Hashes still pending!");
				}
				
			}
		}, 2, 2, TimeUnit.SECONDS);
		
			
		
	}
	
	private static boolean isProcessingFinished(List<Future<?>> hndls){
		boolean done = true;
		for(Future<? >hndl: hndls){
			done =  done && hndl.isDone();
		}
		return done;
	}
	
	

}

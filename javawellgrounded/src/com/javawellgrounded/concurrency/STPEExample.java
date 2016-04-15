package com.javawellgrounded.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class STPEExample {

	/*
	 * ScheduledExecutorService is the backbone of the thread pool classes—it’s versatile and as a result
	 * is quite common. The STPE takes in work in the form of tasks and
	 * schedules them on a pool of threads.
	 */
	private static ScheduledExecutorService stpe;
	private static ScheduledFuture<?> hndl;
	private static BlockingQueue<WorkUnit<String>> lbq = new LinkedBlockingQueue<>();

	public static void main(String[] args) {

		/*
		 * STPE is only one of a number of related executors that can be
		 * obtained very easily by using factory methods available on the
		 * Executors class in java.util.concurrent.
		 */
		stpe = Executors.newScheduledThreadPool(2);

		
		final Runnable msgReader = new Runnable() {

			@Override
			public void run() {
				String nextMsg = lbq.poll().getWork();
				if (nextMsg != null) {
					System.out.println("Msg recvd: " + nextMsg);
				}
			}
		};

		
		stpe.scheduleAtFixedRate(msgReader, 10, 10, TimeUnit.MILLISECONDS);
	}

}

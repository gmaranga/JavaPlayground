package com.javawellgrounded.concurrency;

import java.util.concurrent.BlockingQueue;

public class PrimeNumberGenerator implements Runnable {

	private BlockingQueue<WorkUnit<Integer>> abq;
	
	public PrimeNumberGenerator(BlockingQueue<WorkUnit<Integer>> abq) {
		this.abq = abq;
	}


	private Integer generateNextPrimeNumber(Integer currentPrime) {

		Integer nextPrime = -1;
		
		// loop through the numbers one by one
		for (int i = currentPrime; i < Integer.MAX_VALUE; i++) {

			boolean isPrimeNumber = true;

			// check to see if the number is prime
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrimeNumber = false;
					break; // exit the inner for loop
				}
			}

			// print the number if prime
			if (isPrimeNumber) {
				nextPrime = i;
				break;
			}
		}
		return nextPrime;
	}


	@Override
	public void run() {
		
		WorkUnit<Integer> wu = abq.poll();
		if(wu != null){
			Integer currentPrimeNumber = this.generateNextPrimeNumber(wu.getWork());
			wu.setWork(currentPrimeNumber);
			abq.offer(wu);
			System.out.println("New prime number generated: " + currentPrimeNumber);
		}else{
			System.out.println("Initializing prime number generator: ");
			System.out.println("Current prime number: 1");
			wu = new WorkUnit<Integer>(1);
			abq.offer(wu);
		}
		
	}

}

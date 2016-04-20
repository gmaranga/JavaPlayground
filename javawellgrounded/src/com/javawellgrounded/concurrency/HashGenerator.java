package com.javawellgrounded.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class HashGenerator implements Runnable{

	private BlockingQueue<WorkUnit<Integer>> abq;
	private static final AtomicLong id = new AtomicLong(0);
	private final long hgId;
	
	public HashGenerator(BlockingQueue<WorkUnit<Integer>> abq){
		this.abq = abq;
		this.hgId = nextId();
	}
	
	@Override
	public void run() {
			WorkUnit<Integer> wu = this.abq.peek();
			if(wu != null){
				this.printHashCode(wu.getWork());
			}
			Random rnd = new Random();
			try {
				Thread.sleep(rnd.nextInt(11) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	private long nextId(){
		return id.incrementAndGet();
	}
	
	public void printHashCode(Integer aPrime) {
		final int prime = aPrime;
		int result = 1;
		result = prime * result + this.hashCode();
		System.out.println("[HG" + hgId + "] Prime for hash: " + aPrime);
		System.out.println("[HG" + hgId + "] HashCode: " + result);
	}

}

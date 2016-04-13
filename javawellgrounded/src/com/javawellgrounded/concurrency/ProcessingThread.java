package com.javawellgrounded.concurrency;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
/**
 * Minimum preparation patter implementation
 * using a CountDownlatch
 * @author Gmaranga
 *
 */
public class ProcessingThread extends Thread {

	
	private final String ident;
	private final CountDownLatch latch;
	
	public ProcessingThread(String ident_, CountDownLatch cdl_){
		this.ident = ident_;
		this.latch = cdl_;
	}
	
	public String getIdentifier(){
		return this.ident;
	}
	
	public void initialize(){
		Random rnd = new Random();
		int milis = rnd.nextInt(11);
		try {
			this.sleep(milis * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getIdentifier() + " Initialized");
		latch.countDown();
	}
	
	public void run(){
		initialize();
	}
	
	public static void main(String[] args) {
		
		final int max_threads = 5;
		final int quorum = 1 + (int)(max_threads/2);
		final CountDownLatch cdl = new CountDownLatch(quorum);
		final Set<ProcessingThread> nodes = new HashSet<>();
		
		System.out.println("Max threads: " + max_threads);
		System.out.println("Min threads needed to start: " + quorum);
		try{
		
			for(int i = 0; i < max_threads; i++){
				ProcessingThread local = new ProcessingThread("Node: " + i, cdl);
				nodes.add(local);
				local.start();
			}
			cdl.await();
			System.out.println(quorum + " threads initialized. Running main thread.");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	
}

package com.javawellgrounded.concurrency;

import java.util.concurrent.BlockingQueue;

public class Veterinarian extends Thread {

	private final BlockingQueue<Appointment<Pet>> appts;
	private final int restTime;
	private boolean shutdown = false;
	private int vetId;
	private final int MAX_PATIENS = 10;
	private int patientsExamined = 0;
	
	public Veterinarian(int id, BlockingQueue<Appointment<Pet>> lbq, int pause) {
		this.appts = lbq;
		this.restTime = pause;
		this.vetId = id;
	}

	public synchronized void shutdown() {
		shutdown = true;
	}

	@Override
	public void run() {

		while (!shutdown && patientsExamined < MAX_PATIENS) {
			seePatient();
			try {
				Thread.sleep(restTime * 1000);
			} catch (InterruptedException e) {
				shutdown = true;
			}
		}

	}

	/**
	 * Inside the seePatient() method, the thread will dequeue appointments and
	 * examine the pets corresponding to each in turn, and will block if there
	 * are no appointments currently waiting on the queue.
	 */
	private void seePatient() {
		
		this.patientsExamined++;
		
		try {
			System.out.println("Vet " + vetId + " fetching for a new patient");
			Appointment<Pet> ap = appts.take();
			Pet patient = ap.getPatient();
			System.out.print("Vet " + vetId + " examining Patien: ");
			patient.examine();
			System.out.println(this.patientsExamined + " patients examined by Vet " + vetId);
		} catch (InterruptedException e) {
			shutdown = true;
		}
	}

}

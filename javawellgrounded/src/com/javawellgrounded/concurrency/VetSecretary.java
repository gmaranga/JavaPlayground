package com.javawellgrounded.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VetSecretary extends Thread {

	private final BlockingQueue<Appointment<Pet>> appts;
	private final ConcurrentLinkedQueue<Pet> patientsQueue;
	private final int resttime;
	private final int secId;
	
	public VetSecretary(int id, BlockingQueue<Appointment<Pet>> lbq, ConcurrentLinkedQueue<Pet> aPatientsQueue, int pause){
		this.appts = lbq;
		this.patientsQueue = aPatientsQueue;
		this.resttime = pause;
		this.secId = id;
	}

	@Override
	public void run() {
		placeAppointments();
	}

	private void placeAppointments() {
		
		Pet patient = null;
		
		while ((patient = patientsQueue.poll()) != null) {
			System.out.println("VetSec" + secId + " trying to place appointment");
			appts.offer(new Appointment<Pet>(patient));
			System.out.println("Appoitment for patient: " + patient.name + " placed by VetSec" +  secId);
			try{
				this.sleep(resttime*1000);
			}catch(InterruptedException e){}
		}
	}
	
}

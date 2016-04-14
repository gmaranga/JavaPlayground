package com.javawellgrounded.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BlockingQueueExample {

	public static void main(String[] args) {
		
		final BlockingQueue<Appointment<Pet>> appts = new ArrayBlockingQueue<>(5);
		final List<Pet> patients = new ArrayList<>();
		final int numberOfPatiens = 10;
		
		for (int i = 0; i < numberOfPatiens; i++) {
			
			int petId = i + 1;
			if(patients.isEmpty() || patients.get(i-1) instanceof Cat){
				patients.add(new Dog("Dog" + petId));
			}else{
				patients.add(new Cat("Cat" + petId));
			}
		}
		final ConcurrentLinkedQueue<Pet>patiensQueue = new ConcurrentLinkedQueue<>(patients);
				
		VetSecretary sec1 = new VetSecretary(1, appts, patiensQueue, 6);
		VetSecretary sec2 = new VetSecretary(2, appts, patiensQueue, 9);
		Veterinarian vet = new Veterinarian(1, appts, 3);
		
		sec1.start();
		sec2.start();
		vet.start();
		
	}

}

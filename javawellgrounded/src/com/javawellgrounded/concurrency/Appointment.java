package com.javawellgrounded.concurrency;

public class Appointment<T> {

	private T toBeSeen;
	
	public T getPatient(){ return toBeSeen; }

	public Appointment(T incoming){
		this.toBeSeen = incoming;
	}
}

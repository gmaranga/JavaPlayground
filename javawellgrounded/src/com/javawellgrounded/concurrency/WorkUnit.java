package com.javawellgrounded.concurrency;

public class WorkUnit<T> {

	private T workUnit;
	
	public WorkUnit(T workUnit_) {
		this.workUnit = workUnit_;
	}
	
	public T getWork(){ return this.workUnit; }
	public void setWork(T workUnit){ this.workUnit = workUnit; }
}

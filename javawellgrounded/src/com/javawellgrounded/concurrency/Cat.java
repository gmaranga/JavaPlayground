package com.javawellgrounded.concurrency;

public class Cat extends Pet {

	public Cat(String name){
		super(name);
	}
	
	@Override
	public void examine() {
		System.out.println(this.name + ": Meow!");
	}

}

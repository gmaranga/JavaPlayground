package com.patterns.creational.prototype;

public class PrototypeExample {

	private PrototypeFactory example;
	
	
	
	public PrototypeExample(PrototypeFactory example) {
		super();
		this.example = example;
	}

	public PrototypeFactory makeCopy() throws CloneNotSupportedException{
		return (PrototypeFactory)this.example.clone();
	}

	public static void main(String[] args) {
		
		try{
			PrototypeFactory tempExample = null;
			int num = 1000;
			PrototypeFactory prot = new PrototypeImpl(1000);
			PrototypeExample cm = new PrototypeExample(prot);
			for(int i = 0; i < 10; i++){
				tempExample = cm.makeCopy();
				tempExample.prototypeFactory(i * num);
				tempExample.printValue();
			}
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}

	}

}

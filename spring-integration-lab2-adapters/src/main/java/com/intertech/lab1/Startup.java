package com.intertech.lab1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Startup {

	/**
	 * Adapters are endpoints that connect a message channel to an external
	 * system or technology. Adapters take content from outside of SI and bring
	 * it in as messages into SI channels or take SI messages and deposit their
	 * content to an external system.
	 * 
	 * @param args
	 */
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/si-components.xml");
		while (true) {
		}
	}
}

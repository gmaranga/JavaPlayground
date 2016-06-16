package com.intertech.lab1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Startup {

	/**
	 * Channels make up a significant part of any SI project. They provide the
	 * pipes between all the working components of an EAI project. Transformers,
	 * filters, enrichers, etc. may do the business work of a SI application,
	 * but it is channels that get information and process requests to these
	 * components. Three different type of channels (publish-subscribe,
	 * pollable, and direct channels) in this example. There are other types of
	 * channels in SI, but they all descend from either subscribable or pollable
	 * channels.
	 * 
	 * @param args
	 */
	
	@SuppressWarnings({"resource", "unused"})
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/si-components.xml");
		while(true){
			
		}
	}

	//Code to test pollable channel
	// MessageChannel channel = context.getBean("messageChannel",
	 // MessageChannel.class);
	 // Message<String> message1 = MessageBuilder.withPayload(
	 // "Hello world - one!").build();
	 // Message<String> message2 = MessageBuilder.withPayload(
	 // "Hello world - two!").build();
	 // Message<String> message3 = MessageBuilder.withPayload(
	 // "Hello world - three!").build();
	 // System.out.println("sending message1");
	 // channel.send(message1);
	 // System.out.println("sending message2");
	 // channel.send(message2);
	 // System.out.println("sending message3");
	 // channel.send(message3);
	 // System.out.println("done sending messages");

}

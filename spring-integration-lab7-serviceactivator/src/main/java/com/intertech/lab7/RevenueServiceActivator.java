package com.intertech.lab7;

import org.springframework.messaging.Message;

import com.intertech.domain.Shiporder;
import com.intertech.domain.Shiporder.Item;

public class RevenueServiceActivator {

	private double revenue = 0.0;
	
	/*
	 * A service activator service bean can have multiple service methods.
	 * However, when it does, the configuration of the service activator must
	 * indicate the method to call when a message arrives. When a service
	 * activator has just one public method – as in this case – Spring
	 * Integration knows which method to call and no additional configuration is
	 * needed
	 */
	public Message<Shiporder> adjustRevenue(Message<Shiporder> order){
		
		System.out.println("Processing order");
		for(Item item : order.getPayload().getItem()){
			revenue = revenue +(item.getPrice().doubleValue() * item.getQuantity().intValue());
			System.out.println("Revenue now up to: " + revenue);
		}
		System.out.println("Done processing order");
		
		return order;
	}
	
}

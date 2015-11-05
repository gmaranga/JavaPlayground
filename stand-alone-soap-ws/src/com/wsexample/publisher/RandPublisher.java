package com.wsexample.publisher;

import javax.xml.ws.Endpoint;

import com.wsexample.sib.RandImpl;

public class RandPublisher {
	
	/*
	 * To generate the client side code if necessary:
	 * wsimport -p client -keep http://localhost:8888/rs?wsdl
	 * where -p allows to speciy the package and -keep tells the tool
	 * to the generate the source code in addition to the .class files
	 */
	
	public static void main(String[] args) {
		final String url = "http://localhost:8888/rs";
		System.out.println("Publishing RandService at endpoint " + url);
		Endpoint.publish(url, new RandImpl());
	}
}

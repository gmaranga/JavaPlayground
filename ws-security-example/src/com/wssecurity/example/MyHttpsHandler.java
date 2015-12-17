package com.wssecurity.example;
import com.sun.net.httpserver.HttpHandler;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.HttpExchange;

//The handle method is called on a particular request context,
//in this case on any request to the server that ends with /<uri>.
public class MyHttpsHandler implements HttpHandler{
	
	private IService service;
	
	public MyHttpsHandler(IService service){
		this.service = service;
	}
	
	public void handle(HttpExchange ex){
		//Implement a simple routing table
		String verb = ex.getRequestMethod().toUpperCase();
		if(verb.equals("GET"))	service.doGet(ex);
		else if (verb.equals("POST"))	service.doPost(ex);
		else if (verb.equals("PUT"))	service.doPut(ex);
		else if (verb.equals("DELETE"))	service.doDelete(ex);
		else throw new HTTPException(405);
		
	}

}

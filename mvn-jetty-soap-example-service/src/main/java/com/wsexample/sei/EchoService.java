package com.wsexample.sei;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EchoService {

	
	
	@WebMethod public String echo(String in);
	
}

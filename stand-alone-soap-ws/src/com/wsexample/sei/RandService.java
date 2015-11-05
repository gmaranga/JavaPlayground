package com.wsexample.sei;

import javax.jws.WebMethod;
import javax.jws.WebService;

//Service Endpoint Interface
@WebService
public interface RandService {

	@WebMethod
	public int next1();
	
	@WebMethod
	public int[ ] nextN(final int n);
}

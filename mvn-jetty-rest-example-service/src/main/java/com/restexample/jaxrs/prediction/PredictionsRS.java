package com.restexample.jaxrs.prediction;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/")
public class PredictionsRS {

	
	@Context
	private ServletContext sctx;
}

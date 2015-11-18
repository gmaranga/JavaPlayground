package com.restexample.jaxrs.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/resourcesA")
public class RestfulAdage extends Application {

	/**
	 * The getClasses method is a callback invoked when the RestfulAdage instance is loaded into the servlet
	 *container, specifies the JAX-RS resources available in the WAR file.
	 */
	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> set = new HashSet<>();
		set.add(Adages.class);
		return set;
	}

}

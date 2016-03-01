package com.javawellgrounded.di;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class HollywoodServiceClient {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AgentFinderModule());

		HollywoodServiceGuice hollywoodServiceGuice = injector.
								getInstance(HollywoodServiceGuice.class);

		System.out.println(hollywoodServiceGuice.getFriendlyAgents().get(0).getType());
	}

}

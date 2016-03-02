package com.javawellgrounded.di;

import javax.inject.Provider;


public class AgentFinderProvider implements Provider<AgentFinder> {

	
	/*
	 * Guice supports the JSR-330 Provider<T> interface for this purpose; if you
	 * remember back to the JSR-330 section, you’ll recall the T get() method.
	 * This method is invoked when the AgentFinderModule class configures the
	 * binding of the AgentFinderProvider via the toProvider method.
	 */
	@Override
	public AgentFinder get() {
		return new SpreadsheetAgentFinder();
	}

}

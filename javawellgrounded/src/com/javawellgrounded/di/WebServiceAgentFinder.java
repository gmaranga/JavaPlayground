package com.javawellgrounded.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
//Mark the dependency as singleton
//@Singleton
public class WebServiceAgentFinder implements AgentFinder {

	@Override
	public List<Agent> findAllAgents() {
		List<Agent> agents = new ArrayList<>();
		Agent javaAgent = new Agent("Java Developers");
		Agent pythonAgent = new Agent("Python Developers");
		agents.add(javaAgent);
		agents.add(pythonAgent);
		return agents;
	}

}

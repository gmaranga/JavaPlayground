package com.javawellgrounded.di;

import java.util.ArrayList;
import java.util.List;

public class HollywoodServiceFactoryInjected {

	public static List<Agent> getFriendlyAgents(String agentFinderType){
		
		/*
		 * Not locked to a particular finder implementation. But two issues:
		 * 1 The code is injecting a lookup reference (agentFinderType) as ooposed to the 
		 * real AgentFinder implementation
		 * 2 The getFriendlyAgents method still contains code to find its dependency
		 */
		AgentFinderFactory factory = AgentFinderFactory.getInstance();
		AgentFinder finder = factory.getAgentFinder(agentFinderType);
		List<Agent> agents = finder.findAllAgents();
		List<Agent> javaFriendlyAgents = filterAgents(agents, "Java Developers");
		return javaFriendlyAgents;
	}

	private static List<Agent> filterAgents(List<Agent> agents, String agentType) {

		List<Agent> filteredAgetns = new ArrayList<>();
		for (Agent agent : agents) {
			if(agent.getType().equals(agentType)){
				filteredAgetns.add(agent);
			}
		}
		
		return filteredAgetns;
	}
	
}

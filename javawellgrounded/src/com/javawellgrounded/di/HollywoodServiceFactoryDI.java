package com.javawellgrounded.di;

import java.util.ArrayList;
import java.util.List;

public class HollywoodServiceFactoryDI {

//	@Inject
	public static List<Agent> getFriendlyAgents(AgentFinder finder){
		
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

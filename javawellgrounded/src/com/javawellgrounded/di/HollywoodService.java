package com.javawellgrounded.di;

import java.util.ArrayList;
import java.util.List;

public class HollywoodService {

	
	public static List<Agent> getFriendlyAgents(){
		
		//the code is locked in to using the SpreadsheetAgentFinder implementation of the AgentFinder
		AgentFinder finder = new SpreadsheetAgentFinder();
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

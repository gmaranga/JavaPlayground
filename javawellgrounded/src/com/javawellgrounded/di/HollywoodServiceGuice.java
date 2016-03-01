package com.javawellgrounded.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HollywoodServiceGuice {

	private AgentFinder finder = null;

	@Inject
	public HollywoodServiceGuice(AgentFinder finder) {
		this.finder = finder;
	}

	public List<Agent> getFriendlyAgents() {

		List<Agent> agents = finder.findAllAgents();
		List<Agent> javaFriendlyAgents = filterAgents(agents, "Java Developers");
		return javaFriendlyAgents;
	}

	private static List<Agent> filterAgents(List<Agent> agents, String agentType) {

		List<Agent> filteredAgetns = new ArrayList<>();
		for (Agent agent : agents) {
			if (agent.getType().equals(agentType)) {
				filteredAgetns.add(agent);
			}
		}

		return filteredAgetns;
	}

}

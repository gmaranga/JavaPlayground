package com.javawellgrounded.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class HollywoodServiceGuice {

	private AgentFinder finder = null;

	/*
	 * Binding annotations are used to combine the type of class you want to
	 * inject with an additional identifier in order to identify exactly which
	 * object to inject. 
	 * In this case, you still have the familiar @Inject
	 * annotation, but you supplement that with the @Named annotation to pull in
	 * a particularly named AgentFinder. You configure this @Named type of
	 * binding in your AgentModule by using the annotatedWith method.
	 */
	@Inject
	public HollywoodServiceGuice(@Named("primary")AgentFinder finder) {
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

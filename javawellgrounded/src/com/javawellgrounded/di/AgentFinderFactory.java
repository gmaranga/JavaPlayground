package com.javawellgrounded.di;

public class AgentFinderFactory {

	private AgentFinderFactory(){}
	
	public static AgentFinderFactory getInstance(){
		return new AgentFinderFactory();
	}
	
	public AgentFinder getAgentFinder(String agentFinderType){
		if(agentFinderType.equals("Spreadsheet")){
			return new SpreadsheetAgentFinder();
		}else{
			return new WebServiceAgentFinder();
		}
	}
}

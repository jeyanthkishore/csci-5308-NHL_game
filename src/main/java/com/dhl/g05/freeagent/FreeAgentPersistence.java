package com.dhl.g05.freeagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;

public class FreeAgentPersistence implements IFreeAgentPersistence,IFreeAgentLoad{

	@Override
	public int saveFreeAgentObject(int leagueId, FreeAgentModel freeAgent) {
		StoredProcedure sp= new StoredProcedure();
		String playerName = freeAgent.getPlayerName();
		String position = freeAgent.getPosition();
		int age = freeAgent.getAge();
		double skating = freeAgent.getSkating();
		double shooting = freeAgent.getShooting();
		double checking = freeAgent.getChecking();
		double saving = freeAgent.getSaving();
		int playerId = sp.saveFreeAgent(playerName,position,leagueId,age,skating,shooting,checking,saving);
		return playerId;
	}

	@Override
	public List<IFreeAgent> loadFreeAgentObject(String leagueName) {
		StoredProcedure sp= new StoredProcedure();
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
		List<IFreeAgent> freeAgent = new ArrayList<>();
		String playerName,position;
		int birthDay,birthMonth,birthYear;
		double skating, shooting, checking, saving;
		
		agentValue = sp.fetchAllFreeAgents(leagueName);
		for(HashMap<String, Object> agent : agentValue) {
			playerName = agent.get("agent_name").toString();
			position = agent.get("position_name").toString();
			skating = Double.parseDouble(agent.get("skating").toString());
			shooting = Double.parseDouble(agent.get("shooting").toString());
			checking = Double.parseDouble(agent.get("checking").toString());
			saving = Double.parseDouble(agent.get("saving").toString());
			birthDay=Integer.parseInt(agent.get("birthDay").toString());
			birthMonth=Integer.parseInt(agent.get("birthMonth").toString());
			birthYear=Integer.parseInt(agent.get("birthYear").toString());
			freeAgent.add(new FreeAgentModel(playerName,position, skating, shooting, checking, saving, birthDay,birthMonth,birthYear));
		}
		
		return freeAgent;
	}
	
}

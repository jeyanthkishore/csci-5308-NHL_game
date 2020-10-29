package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgentPersistence;

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
	public List<FreeAgentModel> loadFreeAgentObject(String leagueName) {
		StoredProcedure sp= new StoredProcedure();
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
		List<FreeAgentModel> freeAgent = new ArrayList<FreeAgentModel>();
		String playerName,position;
		int age;
		double skating, shooting, checking, saving;
		
		agentValue = sp.fetchAllFreeAgents(leagueName);
		for(HashMap<String, Object> agent : agentValue) {
			playerName = agent.get("agent_name").toString();
			position = agent.get("position_name").toString();
			age = Integer.parseInt(agent.get("age").toString());
			skating = Double.parseDouble(agent.get("skating").toString());
			shooting = Double.parseDouble(agent.get("shooting").toString());
			checking = Double.parseDouble(agent.get("checking").toString());
			saving = Double.parseDouble(agent.get("saving").toString());
			freeAgent.add(new FreeAgentModel(playerName,position, age, skating, shooting, checking, saving));
		}
		
		return freeAgent;
	}
	
}

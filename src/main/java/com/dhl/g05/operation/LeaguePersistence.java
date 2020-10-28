package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.ILeagueModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueModel;

public class LeaguePersistence implements ILeagueModelPersistence{

	private List<ConferenceModel> conferenceList = new ArrayList<ConferenceModel>();
	private List<FreeAgentModel> freeAgent = new ArrayList<FreeAgentModel>();

	@Override
	public ArrayList<HashMap<String, Object>> loadDetails() {
		StoredProcedure sp= new StoredProcedure();
		ArrayList<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		leagueValue = sp.fetchAllLeagues();
		return leagueValue;
	}

	@Override
	public int saveLeagueObject(LeagueModel leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = leagueObject.getLeagueName();
		String playerName;
		String position;
		double skating,shooting,checking,saving;
		int age;
		int leagueId = sp.saveLeague(leagueName);
		freeAgent = leagueObject.getFreeAgent();
		for(FreeAgentModel free : freeAgent) {
			playerName = free.getPlayerName();
			position = free.getPosition();
			age = free.getAge();
			skating = free.getSkating();
			shooting = free.getShooting();
			checking = free.getChecking();
			saving = free.getSaving();
			int playerId = sp.saveFreeAgent(playerName,position,leagueName,age,skating,shooting,checking,saving);
		}
		return leagueId;
	}

	@Override
	public int loadLeagueObject(String leagueName, LeagueModel league) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		conferenceList = new ArrayList<ConferenceModel>();
		String playerName,position;
		int age;
		double skating, shooting, checking, saving;
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();

		conferenceValue = sp.fetchAllConferences(league_id);
		for(HashMap<String, Object> con : conferenceValue) {
			String conferenceName = con.get("conference_name").toString();
			conferenceList.add(new ConferenceModel(conferenceName,null));
		}
		league.setConferenceDetails(conferenceList);

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
		league.setFreeAgent(freeAgent);
		league.setLeagueName(leagueName);
		return league_id;
	}

}

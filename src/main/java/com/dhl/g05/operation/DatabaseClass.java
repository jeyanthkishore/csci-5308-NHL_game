package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class DatabaseClass implements IDataBasePersistence{
	private LeagueObject leagueObject;
	private List<ConferenceObject> conferenceList = null;
	private List<PlayerObject> freeAgent = null;
	private List<PlayerObject> playerList = null;
	private List<TeamObject> teamList = null;
	private List<DivisionObject> divisionList = null;

	@Override
	public void loadModel(OperationModel operationModel) {
		String conference = operationModel.getConferenceName();
		String league = operationModel.getLeagueName();
		String division = operationModel.getDivisionName();
		String team = operationModel.getTeamName();
		String conferenceName,divisionName,teamName,coachName,managerName;
		String playerName,position;
		Object captain;
		List<HashMap<String,String>> conferenceValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> divisionValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> teamValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> teamDetailValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> playerValue = new ArrayList<HashMap<String,String>>();
		Map<String,Object> playerMap;
		//queryValue = fetchAllConferences(leaguName);
		for(int conSet =0; conSet < conferenceValue.size(); conSet++) {
			conferenceName = conferenceValue.get(conSet).get("conference_name");
			//divisionValue = fetachAllDivision
			for(int divSet =0; divSet < divisionValue.size(); divSet++) {
				divisionName = divisionValue.get(divSet).get("division_name");
				//teamValue = fetachAllTeam()
				for(int teamSet = 0; teamSet < teamValue.size();teamSet++) {
					teamName = teamValue.get(teamSet).get("team_name");
					int teamid = Integer.parseInt(teamValue.get(teamSet).get("team_id"));
					//teamDetailValue = fetchManager(id)
					//playerValue = getPlayerDetails(teamid)
					for(int playerSet = 0;playerSet<playerValue.size();playerSet++) {
						playerMap = new HashMap<String,Object>();
						playerName = playerValue.get(playerSet).get("agent_name").toString();
						position = playerValue.get(playerSet).get("position").toString();
						captain = playerValue.get(playerSet).get("agent_is_captain");
						playerMap.put("playerName", playerName);
						playerMap.put("position", position);
						playerMap.put("captain", captain);
						playerList.add(new PlayerObject(playerMap));
					}
					coachName = teamDetailValue.get(teamSet).get("coach_name");
					managerName = teamDetailValue.get(teamSet).get("manager_name");
					teamList.add(new TeamObject(teamName,coachName,managerName,playerList));
				}
				divisionList.add(new DivisionObject(divisionName,teamList));
			}
			conferenceList.add(new ConferenceObject(conferenceName, divisionList));
		}
		leagueObject.setConferenceDetails(conferenceList);
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
		//queryValue = fetchAllFreeAgents(leaguName);
		for(int agentSet =0;agentSet < agentValue.size();agentSet++) {
			playerMap = new HashMap<String,Object>();
			playerName = agentValue.get(agentSet).get("agent_name").toString();
			position = agentValue.get(agentSet).get("position").toString();
			captain = agentValue.get(agentSet).get("agent_is_captain");
			playerMap.put("playerName", playerName);
			playerMap.put("position", position);
			playerMap.put("captain", captain);
			freeAgent.add(new PlayerObject(playerMap));
		}
		leagueObject.setFreeAgent(freeAgent);
	operationModel.setLeagueObject(leagueObject);
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkLeagueExistence(OperationModel operationModel) {
		List<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		//queryValue = checkLeague(leagueName);
		if(leagueValue.isEmpty()) {
			return false;
		}
		return true;
	}

}

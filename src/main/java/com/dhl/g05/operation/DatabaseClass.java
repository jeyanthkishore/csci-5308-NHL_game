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
		Boolean captain;
		List<HashMap<String,String>> conferenceValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> divisionValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> teamValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> teamDetailValue = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> playerValue = new ArrayList<HashMap<String,String>>();
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
						playerName = playerValue.get(playerSet).get("agent_name").toString();
						position = playerValue.get(playerSet).get("position").toString();
						captain = Boolean.parseBoolean(playerValue.get(playerSet).get("agent_is_captain"));
						playerList.add(new PlayerObject(playerName,position,captain));
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
			playerName = agentValue.get(agentSet).get("agent_name").toString();
			position = agentValue.get(agentSet).get("position").toString();
			captain = Boolean.parseBoolean(agentValue.get(agentSet).get("agent_is_captain").toString());
			freeAgent.add(new PlayerObject(playerName,position,captain));
		}
		leagueObject.setFreeAgent(freeAgent);
	operationModel.setLeagueObject(leagueObject);
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		LeagueObject league = new LeagueObject();
		String leagueName = league.getLeagueName();
		String conferenceName = "";
		String divisionName = "";
		String teamName = "";
		String managerName = "";
		String coachName ="";
		String playerName = "";
		String position = "";
		Object captain = null;
		//int leagueId = saveLeague(leagueName);
		conferenceList = league.getConferenceDetails();
		for(int conSet = 0; conSet < conferenceList.size();conSet++) {
			conferenceName = conferenceList.get(conSet).getConferenceName();
		//	int conferenceId = saveConference(leagueId,conferenceName) ;
			divisionList = conferenceList.get(conSet).getDivisionDetails();
			for(int divSet = 0; divSet < divisionList.size();divSet++) {
				divisionName = divisionList.get(divSet).getDivisionName();
				//int divId = saveDivision(divisionName.conferenceId);
				teamList = divisionList.get(divSet).getTeamDetails();
				for(int teamSet = 0; teamSet < teamList.size();teamSet++) {
					teamName = teamList.get(teamSet).getTeamName();
					managerName = teamList.get(teamSet).getGeneralManagerName();
					coachName = teamList.get(teamSet).getHeadCoachName();
					//int teamId = saveTeam(teamName,managerName,divId,coachName);
					playerList = teamList.get(teamSet).getPlayerList();
					for(int playerSet = 0; playerSet< playerList.size();playerSet++) {
						playerName = playerList.get(playerSet).getPlayerName();
						position = playerList.get(playerSet).getPostition();
						//int positionId = getPositionId(position);
						captain = playerList.get(playerSet).getCaptain();
						//int playerId = savePlayer(teamId,positionId,player_name,captain);
					}
				}
			}
		}
		
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

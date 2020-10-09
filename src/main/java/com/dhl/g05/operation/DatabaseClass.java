package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.database.StoredProcedure;
import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class DatabaseClass implements IDataBasePersistence{
	private LeagueObject leagueObject = new LeagueObject();
	private List<ConferenceObject> conferenceList = new ArrayList<ConferenceObject>();
	private List<PlayerObject> freeAgent = new ArrayList<PlayerObject>();
	private List<PlayerObject> playerList = new ArrayList<PlayerObject>();
	private List<TeamObject> teamList = new ArrayList<TeamObject>();
	private List<DivisionObject> divisionList = new ArrayList<DivisionObject>();

	@Override
	public void loadModel(OperationModel operationModel) {
//		StoredProcedure sp= new StoredProcedure();
		String conference = operationModel.getConferenceName();
		String league = operationModel.getLeagueName();
		String division = operationModel.getDivisionName();
		String team = operationModel.getTeamName();
		String conferenceName,divisionName,teamName,coachName,managerName;
		String playerName,position;
		Boolean captain;
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,Object>> divisionValue ;
		List<HashMap<String,Object>> teamValue;
		List<HashMap<String,Object>> teamDetailValue;
		List<HashMap<String,Object>> playerValue;
		//conferenceValue = sp.fetchAllConferences("DHL");
		for(int conSet =0; conSet < conferenceValue.size(); conSet++) {
			conferenceName = conferenceValue.get(conSet).get("conference_name").toString();
			divisionList = new ArrayList<DivisionObject>();
			divisionValue = new ArrayList<HashMap<String,Object>>();
//			divisionValue = sp.fetchAllDivisions(league, conferenceName);
			for(int divSet =0; divSet < divisionValue.size(); divSet++) {
				divisionName = divisionValue.get(divSet).get("division_name").toString();
				teamList = new ArrayList<TeamObject>();
				teamValue = new ArrayList<HashMap<String,Object>>();
//				teamValue = sp.fetchAllTeams(league, conferenceName, divisionName);
				for(int teamSet = 0; teamSet < teamValue.size();teamSet++) {
					teamName = teamValue.get(teamSet).get("team_name").toString();
					int teamid = Integer.parseInt(teamValue.get(teamSet).get("team_id").toString());
					teamDetailValue = new ArrayList<HashMap<String,Object>>();
//					teamDetailValue = sp.fetchManagerCoach(teamid);
					playerList = new ArrayList<PlayerObject>();
					playerValue = new ArrayList<HashMap<String,Object>>();
//					playerValue = sp.fetchAllPlayers(teamid);
					for(int playerSet = 0;playerSet<playerValue.size();playerSet++) {
						playerName = playerValue.get(playerSet).get("agent_name").toString();
						position = playerValue.get(playerSet).get("position").toString();
						captain = Boolean.parseBoolean(playerValue.get(playerSet).get("agent_is_captain").toString());
						playerList.add(new PlayerObject(playerName,position,captain));
					}
					coachName = teamDetailValue.get(0).get("coach_name").toString();
					managerName = teamDetailValue.get(0).get("manager_name").toString();
					teamList.add(new TeamObject(teamName,coachName,managerName,playerList));
				}
				divisionList.add(new DivisionObject(divisionName,teamList));
			}
			conferenceList.add(new ConferenceObject(conferenceName, divisionList));
		}
		leagueObject.setConferenceDetails(conferenceList);
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
//		agentValue = sp.fetchAllFreeAgents(league);
		for(int agentSet =0;agentSet < agentValue.size();agentSet++) {
			playerName = agentValue.get(agentSet).get("agent_name").toString();
			position = agentValue.get(agentSet).get("position_name").toString();
			captain = Boolean.parseBoolean(agentValue.get(agentSet).get("agent_is_captain").toString());
			freeAgent.add(new PlayerObject(playerName,position,captain));
		}
		leagueObject.setFreeAgent(freeAgent);
	operationModel.setLeagueObject(leagueObject);
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		LeagueObject league = operationModel.getLeagueObject();
//		StoredProcedure sp= new StoredProcedure();
		String leagueName = league.getLeagueName();
		String conferenceName = "";
		String divisionName = "";
		String teamName = "";
		String managerName = "";
		String coachName ="";
		String playerName = "";
		String position = "";
		Boolean captain = null;
//		int leagueId = sp.saveLeague(leagueName);
		conferenceList = league.getConferenceDetails();
		for(int conSet = 0; conSet < conferenceList.size();conSet++) {
			conferenceName = conferenceList.get(conSet).getConferenceName();
//			int conferenceId = sp.saveConference(leagueId,conferenceName) ;
			divisionList = conferenceList.get(conSet).getDivisionDetails();
			for(int divSet = 0; divSet < divisionList.size();divSet++) {
				divisionName = divisionList.get(divSet).getDivisionName();
//				int divId = sp.saveDivision(divisionName,conferenceId);
				teamList = divisionList.get(divSet).getTeamDetails();
				for(int teamSet = 0; teamSet < teamList.size();teamSet++) {
					teamName = teamList.get(teamSet).getTeamName();
					managerName = teamList.get(teamSet).getGeneralManagerName();
					coachName = teamList.get(teamSet).getHeadCoachName();
//					int teamId = sp.saveTeam(teamName,managerName,divId,coachName);
					playerList = teamList.get(teamSet).getPlayerList();
					for(int playerSet = 0; playerSet< playerList.size();playerSet++) {
						playerName = playerList.get(playerSet).getPlayerName();
						position = playerList.get(playerSet).getPosition();
						int positionId = 1;
						captain = playerList.get(playerSet).getCaptain();
						int captainID = (captain) ? 1 : 0;
//						int playerId = sp.savePlayer(teamId,positionId,playerName,captainID);
					}
				}
			}
		}
		
	}

	@Override
	public boolean checkLeagueExistence(OperationModel operationModel) {
		List<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
//		StoredProcedure sp= new StoredProcedure();
//		int leagueId = sp.getLeagueID(operationModel.getLeagueName());
		if(leagueValue.isEmpty()) {
			return false;
		}
		return true;
	}

}

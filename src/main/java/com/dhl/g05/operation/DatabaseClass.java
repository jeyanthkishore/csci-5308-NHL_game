package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.FreeAgentObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class DatabaseClass implements IDataBasePersistence{
	private LeagueObject leagueObject = new LeagueObject();
	private List<ConferenceObject> conferenceList = new ArrayList<ConferenceObject>();
	private List<FreeAgentObject> freeAgent = new ArrayList<FreeAgentObject>();
	private List<PlayerObject> playerList = new ArrayList<PlayerObject>();
	private List<TeamObject> teamList = new ArrayList<TeamObject>();
	private List<DivisionObject> divisionList = new ArrayList<DivisionObject>();

	@Override
	public void loadModel(OperationModel operationModel) {
		StoredProcedure sp= new StoredProcedure();
		String league_name = operationModel.getLeagueName();
		int league_id = sp.getLeagueID(league_name);
		String conferenceName,divisionName,teamName,coachName,managerName;
		String playerName,position;
		Boolean captain;
		int conferenceID,divisonId;
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,Object>> divisionValue ;
		List<HashMap<String,Object>> teamValue;
		List<HashMap<String,Object>> teamDetailValue;
		List<HashMap<String,Object>> playerValue;
		conferenceValue = sp.fetchAllConferences(league_id);
		for(int conSet =0; conSet < conferenceValue.size(); conSet++) {
			conferenceName = conferenceValue.get(conSet).get("conference_name").toString();
			conferenceID = Integer.parseInt(conferenceValue.get(conSet).get("conference_id").toString());
			divisionList = new ArrayList<DivisionObject>();
			divisionValue = new ArrayList<HashMap<String,Object>>();
			divisionValue = sp.fetchAllDivisions(conferenceID);
			for(int divSet =0; divSet < divisionValue.size(); divSet++) {
				divisionName = divisionValue.get(divSet).get("division_name").toString();
				divisonId = Integer.parseInt(divisionValue.get(divSet).get("division_id").toString());
				teamList = new ArrayList<TeamObject>();
				teamValue = new ArrayList<HashMap<String,Object>>();
				teamValue = sp.fetchAllTeams(divisonId);
				for(int teamSet = 0; teamSet < teamValue.size();teamSet++) {
					teamName = teamValue.get(teamSet).get("team_name").toString();
					int teamid = Integer.parseInt(teamValue.get(teamSet).get("team_id").toString());
					teamDetailValue = new ArrayList<HashMap<String,Object>>();
					teamDetailValue = sp.fetchManagerCoach(teamid);
					playerList = new ArrayList<PlayerObject>();
					playerValue = new ArrayList<HashMap<String,Object>>();
					playerValue = sp.fetchAllPlayers(teamid);
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
		agentValue = sp.fetchAllFreeAgents(league_name);
		for(int agentSet =0;agentSet < agentValue.size();agentSet++) {
			playerName = agentValue.get(agentSet).get("agent_name").toString();
			position = agentValue.get(agentSet).get("position_name").toString();
			freeAgent.add(new FreeAgentObject(playerName,position));
		}
		leagueObject.setFreeAgent(freeAgent);
		leagueObject.setLeagueName(league_name);
	operationModel.setLeagueObject(leagueObject);
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		LeagueObject league = operationModel.getLeagueObject();
		StoredProcedure sp= new StoredProcedure();
		String leagueName = league.getLeagueName();
		String conferenceName = "";
		String divisionName = "";
		String teamName = "";
		String managerName = "";
		String coachName ="";
		String playerName = "";
		String position = "";
		Boolean captain = null;
		int leagueId = sp.saveLeague(leagueName);
		conferenceList = league.getConferenceDetails();
		for(int conSet = 0; conSet < conferenceList.size();conSet++) {
			conferenceName = conferenceList.get(conSet).getConferenceName();
			int conferenceId = sp.saveConference(leagueId,conferenceName) ;
			divisionList = conferenceList.get(conSet).getDivisionDetails();
			for(int divSet = 0; divSet < divisionList.size();divSet++) {
				divisionName = divisionList.get(divSet).getDivisionName();
				int divId = sp.saveDivision(divisionName,conferenceId);
				teamList = divisionList.get(divSet).getTeamDetails();
				for(int teamSet = 0; teamSet < teamList.size();teamSet++) {
					teamName = teamList.get(teamSet).getTeamName();
					managerName = teamList.get(teamSet).getGeneralManagerName();
					coachName = teamList.get(teamSet).getHeadCoachName();
					int teamId = sp.saveTeam(teamName,managerName,divId,coachName);
					playerList = teamList.get(teamSet).getPlayerList();
					for(int playerSet = 0; playerSet< playerList.size();playerSet++) {
						playerName = playerList.get(playerSet).getPlayerName();
						position = playerList.get(playerSet).getPosition();
						int positionId = sp.getPositionID(position);
						captain = playerList.get(playerSet).getCaptain();
						int captainID = (captain) ? 1 : 0;
						int playerId = sp.savePlayer(teamId,positionId,playerName,captainID);
					}
				}
			}
		}
		freeAgent = league.getFreeAgent();
		for(int playerSet = 0; playerSet< freeAgent.size();playerSet++) {
			playerName = playerList.get(playerSet).getPlayerName();
			position = playerList.get(playerSet).getPosition();
			int positionId = sp.getPositionID(position);
			int captainId = 1;
			int playerId = sp.saveFreeAgent(playerName,position,captainId,leagueName);
		}
	}

	@Override
	public void loadDetails(OperationModel operationModel) {
		StoredProcedure sp= new StoredProcedure();
		ArrayList<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		leagueValue = sp.fetchAllLeagues();
		operationModel.setLeagueCheck(leagueValue);
	}

}

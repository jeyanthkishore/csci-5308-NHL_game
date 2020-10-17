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
//		StoredProcedure sp= new StoredProcedure();
//		String league_name = operationModel.getLeagueName();
//		int league_id = sp.getLeagueID(league_name);
//		String conferenceName,divisionName,teamName,coachName,managerName;
//		String playerName,position;
//		Boolean captain;
//		int conferenceID,divisonId;
//		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
//		List<HashMap<String,Object>> divisionValue ;
//		List<HashMap<String,Object>> teamValue;
//		List<HashMap<String,Object>> teamDetailValue;
//		List<HashMap<String,Object>> playerValue;
//		conferenceValue = sp.fetchAllConferences(league_id);
//		for(int conSet =0; conSet < conferenceValue.size(); conSet++) {
//			conferenceName = conferenceValue.get(conSet).get("conference_name").toString();
//			conferenceID = Integer.parseInt(conferenceValue.get(conSet).get("conference_id").toString());
//			divisionList = new ArrayList<DivisionObject>();
//			divisionValue = new ArrayList<HashMap<String,Object>>();
//			divisionValue = sp.fetchAllDivisions(conferenceID);
//			for(int divSet =0; divSet < divisionValue.size(); divSet++) {
//				divisionName = divisionValue.get(divSet).get("division_name").toString();
//				divisonId = Integer.parseInt(divisionValue.get(divSet).get("division_id").toString());
//				teamList = new ArrayList<TeamObject>();
//				teamValue = new ArrayList<HashMap<String,Object>>();
//				teamValue = sp.fetchAllTeams(divisonId);
//				for(int teamSet = 0; teamSet < teamValue.size();teamSet++) {
//					teamName = teamValue.get(teamSet).get("team_name").toString();
//					int teamid = Integer.parseInt(teamValue.get(teamSet).get("team_id").toString());
//					teamDetailValue = new ArrayList<HashMap<String,Object>>();
//					teamDetailValue = sp.fetchManagerCoach(teamid);
//					playerList = new ArrayList<PlayerObject>();
//					playerValue = new ArrayList<HashMap<String,Object>>();
//					playerValue = sp.fetchAllPlayers(teamid);
//					for(int playerSet = 0;playerSet<playerValue.size();playerSet++) {
//						playerName = playerValue.get(playerSet).get("agent_name").toString();
//						position = playerValue.get(playerSet).get("position").toString();
//						captain = Boolean.parseBoolean(playerValue.get(playerSet).get("agent_is_captain").toString());
//						playerList.add(new PlayerObject(playerName,position,captain));
//					}
//					coachName = teamDetailValue.get(0).get("coach_name").toString();
//					managerName = teamDetailValue.get(0).get("manager_name").toString();
//					teamList.add(new TeamObject(teamName,coachName,managerName,playerList));
//				}
//				divisionList.add(new DivisionObject(divisionName,teamList));
//			}
//			conferenceList.add(new ConferenceObject(conferenceName, divisionList));
//		}
//		leagueObject.setConferenceDetails(conferenceList);
//		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
//		agentValue = sp.fetchAllFreeAgents(league_name);
//		for(int agentSet =0;agentSet < agentValue.size();agentSet++) {
//			playerName = agentValue.get(agentSet).get("agent_name").toString();
//			position = agentValue.get(agentSet).get("position_name").toString();
//			freeAgent.add(new FreeAgentObject(playerName,position));
//		}
//		leagueObject.setFreeAgent(freeAgent);
//		leagueObject.setLeagueName(league_name);
//	operationModel.setLeagueObject(leagueObject);
	}

	@Override
	public int loadLeagueObject(String leagueName, LeagueObject league) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		conferenceList = new ArrayList<ConferenceObject>();
		conferenceValue = sp.fetchAllConferences(league_id);
		for(int conSet =0; conSet < conferenceValue.size(); conSet++) {
			String conferenceName = conferenceValue.get(conSet).get("conference_name").toString();
			conferenceList.add(new ConferenceObject(conferenceName,null));
		}
		league.setConferenceDetails(conferenceList);
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
		agentValue = sp.fetchAllFreeAgents(leagueName);
		String playerName,position;
		for(int agentSet =0;agentSet < agentValue.size();agentSet++) {
			playerName = agentValue.get(agentSet).get("agent_name").toString();
			position = agentValue.get(agentSet).get("position_name").toString();
			freeAgent.add(new FreeAgentObject(playerName,position));
		}
		league.setFreeAgent(freeAgent);
		league.setLeagueName(leagueName);
		return league_id;
	}
	
	@Override
	public int loadConferenceObject(int leagueId, ConferenceObject conferenceObject) {
		String conferenceName = conferenceObject.getConferenceName();
		List<HashMap<String, Object>> divisionValue = new ArrayList<HashMap<String,Object>>();
		StoredProcedure sp= new StoredProcedure();
		int conferenceId = sp.getConferenceID(conferenceName, leagueId);
		divisionValue = sp.fetchAllDivisions(conferenceId);
		divisionList = new ArrayList<DivisionObject>();
		for(int divSet =0; divSet < divisionValue.size(); divSet++) {
			String divisionName = divisionValue.get(divSet).get("division_name").toString();
			divisionList.add(new DivisionObject(divisionName,null));
		}
		conferenceObject.setDivisionDetails(divisionList);
		return conferenceId;
	}
	
	@Override
	public int loadDivisionObject(int conferenceId, DivisionObject divisionObject) {
		String divisionName = divisionObject.getDivisionName();
		StoredProcedure sp= new StoredProcedure();
		String teamName,coachName,managerName;
		List<HashMap<String, Object>> teamValue = new ArrayList<HashMap<String,Object>>();
		int divisonId = sp.getDivisionID(divisionName, conferenceId);
		teamValue = sp.fetchAllTeams(divisonId);
		teamList = new ArrayList<TeamObject>();
		for(int teamSet = 0; teamSet < teamValue.size();teamSet++) {
			teamName = teamValue.get(teamSet).get("team_name").toString();
			coachName = teamValue.get(teamSet).get("coach_name").toString();
			managerName = teamValue.get(teamSet).get("manager_name").toString();
			teamList.add(new TeamObject(teamName,coachName,managerName,null));
		}
		divisionObject.setTeamDetails(teamList);
		return divisonId;
	}
	@Override
	public int loadTeamObject(int divisionId, TeamObject teamObject) {
		String teamName = teamObject.getTeamName();
		StoredProcedure sp= new StoredProcedure();
		int teamId = sp.getTeamID(teamName, divisionId);
		List<HashMap<String, Object>> playerValue = new ArrayList<HashMap<String,Object>>();
		playerValue = sp.fetchAllPlayers(teamId);
		for(int playerSet = 0;playerSet<playerValue.size();playerSet++) {
			String playerName = playerValue.get(playerSet).get("player_name").toString();
			playerList.add(new PlayerObject(playerName,null,null));
		}
		teamObject.setPlayerList(playerList);
		return teamId;
	}
	@Override
	public int loadPlayerObject(int teamId, PlayerObject playerObject) {
		String playerName = playerObject.getPlayerName();
		StoredProcedure sp= new StoredProcedure();
		int playerId = sp.getPlayerID(teamId,playerName);
		List<HashMap<String, Object>> playerDetail = new ArrayList<HashMap<String,Object>>();
		playerDetail = sp.fetchPlayerDetails(playerId);
		String position = playerDetail.get(0).get("position_name").toString();
		Boolean captain = Boolean.parseBoolean(playerDetail.get(0).get("player_is_captain").toString());
		playerObject.setPosition(position);
		playerObject.setCaptain(captain);
		return playerId;
	}

	@Override
	public void saveModel(OperationModel operationModel) {
//		LeagueObject league = operationModel.getLeagueObject();
//		StoredProcedure sp= new StoredProcedure();
//		String leagueName = league.getLeagueName();
//		String conferenceName = "";
//		String divisionName = "";
//		String teamName = "";
//		String managerName = "";
//		String coachName ="";
//		String playerName = "";
//		String position = "";
//		Boolean captain = null;
//		int leagueId = sp.saveLeague(leagueName);
//		conferenceList = league.getConferenceDetails();
//		for(int conSet = 0; conSet < conferenceList.size();conSet++) {
//			conferenceName = conferenceList.get(conSet).getConferenceName();
//			int conferenceId = sp.saveConference(leagueId,conferenceName) ;
//			divisionList = conferenceList.get(conSet).getDivisionDetails();
//			for(int divSet = 0; divSet < divisionList.size();divSet++) {
//				divisionName = divisionList.get(divSet).getDivisionName();
//				int divId = sp.saveDivision(divisionName,conferenceId);
//				teamList = divisionList.get(divSet).getTeamDetails();
//				for(int teamSet = 0; teamSet < teamList.size();teamSet++) {
//					teamName = teamList.get(teamSet).getTeamName();
//					managerName = teamList.get(teamSet).getGeneralManagerName();
//					coachName = teamList.get(teamSet).getHeadCoachName();
//					int managerId = sp.saveManager(managerName);
//					int coachId = sp.saveCoach(coachName);
//					int teamId = sp.saveTeam(teamName,managerId,divId,coachId);
//					playerList = teamList.get(teamSet).getPlayerList();
//					for(int playerSet = 0; playerSet< playerList.size();playerSet++) {
//						playerName = playerList.get(playerSet).getPlayerName();
//						position = playerList.get(playerSet).getPosition();
//						int positionId = sp.getPositionID(position);
//						captain = playerList.get(playerSet).getCaptain();
//						int captainID = (captain) ? 1 : 0;
//						int playerId = sp.savePlayer(teamId,positionId,playerName,captainID);
//					}
//				}
//			}
//		}
//		freeAgent = league.getFreeAgent();
//		for(int playerSet = 0; playerSet< freeAgent.size();playerSet++) {
//			playerName = playerList.get(playerSet).getPlayerName();
//			position = playerList.get(playerSet).getPosition();
//			int playerId = sp.saveFreeAgent(playerName,position,leagueName);
//		}
//		operationModel.setResult("success");
	}

	@Override
	public int saveLeagueObject(LeagueObject leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = leagueObject.getLeagueName();
		String playerName;
		String position;
		int leagueId = sp.saveLeague(leagueName);
		freeAgent = leagueObject.getFreeAgent();
		for(int playerSet = 0; playerSet< freeAgent.size();playerSet++) {
			playerName = freeAgent.get(playerSet).getPlayerName();
			position = freeAgent.get(playerSet).getPosition();
			int playerId = sp.saveFreeAgent(playerName,position,leagueName);
		}
		return leagueId;
	}
	
	@Override
	public int saveConferenceObject(int leagueId, ConferenceObject conferenceObject) {
		StoredProcedure sp= new StoredProcedure();
		String conferenceName = conferenceObject.getConferenceName();
		int conferenceId = sp.saveConference(leagueId,conferenceName) ;
		return conferenceId;
	}
	
	@Override
	public int saveDivisionObject(int conferenceId, DivisionObject divisionObject) {
		StoredProcedure sp= new StoredProcedure();
		String divisionName = divisionObject.getDivisionName();
		int divisionId = sp.saveDivision(divisionName,conferenceId);
		return divisionId;
	}
	
	@Override
	public int saveTeamObject(int divisionId, TeamObject teamObject) {
		StoredProcedure sp= new StoredProcedure();
		String teamName = teamObject.getTeamName();
		String managerName = teamObject.getGeneralManagerName();
		String coachName = teamObject.getHeadCoachName();
		int managerId = sp.saveManager(managerName);
		int coachId = sp.saveCoach(coachName);
		int teamId = sp.saveTeam(teamName,managerId,divisionId,coachId);
		return teamId;
	}
	
	@Override
	public int savePlayerObject(int teamId, PlayerObject playerObject) {
		StoredProcedure sp= new StoredProcedure();
		String playerName = playerObject.getPlayerName();
		String position = playerObject.getPosition();
		int positionId = sp.getPositionID(position);
		Boolean captain = playerObject.getCaptain();
		int captainID = (captain) ? 1 : 0;
		int playerId = sp.savePlayer(teamId,positionId,playerName,captainID);
		return playerId;
	}
	
	@Override
	public void loadDetails(OperationModel operationModel) {
		StoredProcedure sp= new StoredProcedure();
		ArrayList<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		leagueValue = sp.fetchAllLeagues();
		operationModel.setLeagueCheck(leagueValue);
	}
}

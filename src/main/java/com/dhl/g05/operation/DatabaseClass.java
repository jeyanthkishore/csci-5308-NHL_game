package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.DateStoredProcedure;
import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.FreeAgentObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;
import com.dhl.g05.simulation.Date;

public class DatabaseClass implements IDataBasePersistence{
	private List<ConferenceObject> conferenceList = new ArrayList<ConferenceObject>();
	private List<FreeAgentObject> freeAgent = new ArrayList<FreeAgentObject>();
	private List<PlayerObject> playerList = new ArrayList<PlayerObject>();
	private List<TeamObject> teamList = new ArrayList<TeamObject>();
	private List<DivisionObject> divisionList = new ArrayList<DivisionObject>();


	@Override
	public int loadLeagueObject(String leagueName, LeagueObject league) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String, Object>> conferenceValue = new ArrayList<HashMap<String,Object>>();
		conferenceList = new ArrayList<ConferenceObject>();
		conferenceValue = sp.fetchAllConferences(league_id);
		for(HashMap<String, Object> con : conferenceValue) {
			String conferenceName = con.get("conference_name").toString();
			conferenceList.add(new ConferenceObject(conferenceName,null));
		}
		league.setConferenceDetails(conferenceList);
		List<HashMap<String,Object>> agentValue = new ArrayList<HashMap<String,Object>>();
		agentValue = sp.fetchAllFreeAgents(leagueName);
		String playerName,position;
		for(HashMap<String, Object> agent : agentValue) {
			playerName = agent.get("agent_name").toString();
			position = agent.get("position_name").toString();
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
		for(HashMap<String, Object> div : divisionValue) {
			String divisionName = div.get("division_name").toString();
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
		for(HashMap<String, Object> team : teamValue) {
			teamName = team.get("team_name").toString();
			coachName = team.get("coach_name").toString();
			managerName = team.get("manager_name").toString();
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
		for(HashMap<String, Object> player : playerValue) {
			String playerName = player.get("player_name").toString();
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
	public int saveLeagueObject(LeagueObject leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		String leagueName = leagueObject.getLeagueName();
		String playerName;
		String position;
		int leagueId = sp.saveLeague(leagueName);
		freeAgent = leagueObject.getFreeAgent();
		for(FreeAgentObject free : freeAgent) {
			playerName = free.getPlayerName();
			position = free.getPosition();
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
	public ArrayList<HashMap<String, Object>> loadDetails() {
		StoredProcedure sp= new StoredProcedure();
		ArrayList<HashMap<String,Object>> leagueValue = new ArrayList<HashMap<String,Object>>();
		leagueValue = sp.fetchAllLeagues();
		return leagueValue;
	}
	
	@Override
	public void saveDate(LeagueObject league, Date date) {
		
		StoredProcedure sp = new StoredProcedure();
		
		int leagueID = sp.getLeagueID(league.getLeagueName());
		
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		int daysSinceStatIncreaseCheck = date.getDaysSinceStatIncreaseCheck();
		int daysUntilStatIncreaseCheck = date.getDaysUntilStatIncreaseCheck();
		
		DateStoredProcedure dsp = new DateStoredProcedure();
		dsp.saveDate(leagueID,day,month,year,daysSinceStatIncreaseCheck,daysUntilStatIncreaseCheck);
	}
	
	

	@Override
	public void loadDate(LeagueObject league,Date date) {
		
		StoredProcedure sp = new StoredProcedure();
		
		int leagueID = sp.getLeagueID(league.getLeagueName());
		
		DateStoredProcedure dsp = new DateStoredProcedure();
		
		List<Integer> result = dsp.loadDate(leagueID);
		
		date.setDay(result.get(0));
		date.setMonth(result.get(1));
		date.setYear(result.get(2));
		date.setDaysSinceStatIncreaseCheck(result.get(3));
		date.setDaysUntilStatIncreaseCheck(result.get(4));
	
	}
}

package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class DbPersistanceMock implements IDataBasePersistence{

	@Override
	public void loadModel(OperationModel operationModel) {
		if(operationModel.getLeagueName().equals("HockeyLeague")) {
			JsonMockDataDb mockData = new JsonMockDataDb();
			operationModel.setLeagueObject(mockData.getLeague());
		}else {
			operationModel.setLeagueObject(null);
		}
		
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		if(operationModel!=null) {
			operationModel.setResult("success");
		}
	}

	
	public boolean checkLeagueExistence(OperationModel operationModel) {
		if(operationModel.getLeagueName().equalsIgnoreCase("HockeyLeague")) {
			return true;
		}
		return false;
	}

	
	public void loadNewTeams(OperationModel operationModel) {
		ArrayList<HashMap<String,Object>> teamNames = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> team = new HashMap<String,Object>();
		team.put("team_name","Dog");
		teamNames.add(team);
		team.put("team_name","Cat");
		teamNames.add(team);
		operationModel.setNewTeam(teamNames);
	}

	@Override
	public void loadDetails(OperationModel operationModel) {
		ArrayList<HashMap<String,Object>> leagueNames = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> league = new HashMap<String,Object>();
		league.put("league_name","HockeyLeague");
		leagueNames.add(league);
		league = new HashMap<String,Object>();
		league.put("league_name","CanadaLeague");
		leagueNames.add(league);
		operationModel.setLeagueCheck(leagueNames);
	}

	@Override
	public int saveLeagueObject(LeagueObject leagueObject) {
		if(leagueObject.getLeagueName().equals("HockeyLeague")) {
			return 1;
		}
		return 0;
	}
	@Override
	public int saveConferenceObject(int leagueId,ConferenceObject object) {
		if(leagueId==1 && object.getConferenceName().equals("Western Conference")) {
			return 1;
		}
		return 0;
	}
	@Override
	public int saveDivisionObject(int conferenceId,DivisionObject object) {
		if(conferenceId==1 && object.getDivisionName().equals("Atlantic")) {
			return 1;
		}
		return 0;
	}
	@Override
	public int saveTeamObject(int divisionId,TeamObject object) {
		if(divisionId==1 && object.getTeamName().equals("Striker Six")) {
			return 1;
		}
		return 0;
	}
	@Override
	public int savePlayerObject(int teamId,PlayerObject object) {
		if(teamId==1 && object.getPlayerName().equals("Cristiano Ronaldo")) {
			return 1;
		}
		return 0;
	}

	@Override
	public int loadLeagueObject(String leagueName,LeagueObject leagueObject) {
		if(leagueName.equalsIgnoreCase("HockeyLeague")) {
			ArrayList<ConferenceObject> conferences = new ArrayList<>();
			conferences.add(new ConferenceObject("Western Conference",null));
			leagueObject.setConferenceDetails(conferences);;
			return 1;
		}
		return 0;
	}

	@Override
	public int loadConferenceObject(int leagueId, ConferenceObject conferenceObject) {
		if(leagueId==1 && conferenceObject.getConferenceName().equals("Western Conference")) {
			ArrayList<DivisionObject> divisions = new ArrayList<>();
			divisions.add(new DivisionObject("Atlantic",null));
			conferenceObject.setDivisionDetails(divisions);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadDivisionObject(int conferenceId, DivisionObject divisionObject) {
		if(conferenceId==1 && divisionObject.getDivisionName().equals("Atlantic")) {
			ArrayList<TeamObject> teams = new ArrayList<>();
			teams.add(new TeamObject("Striker Six",null,null,null));
			divisionObject.setTeamDetails(teams);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadTeamObject(int divisionId, TeamObject teamObject) {
		if(divisionId==1 && teamObject.getTeamName().equals("Striker Six")) {
			ArrayList<PlayerObject> players = new ArrayList<>();
			players.add(new PlayerObject("Cristiano Ronaldo",null,null));
			teamObject.setPlayerList(players);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadPlayerObject(int teamId, PlayerObject playerObject) {
		if(teamId==1 && playerObject.getPlayerName().equals("Cristiano Ronaldo")) {
			return 1;
		}
		return 0;
	}
	
	
}

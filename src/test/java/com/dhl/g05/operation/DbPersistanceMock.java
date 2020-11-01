package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.coach.ICoachModelPersistence;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.conference.IConferenceModelPersistence;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.division.IDivisionModelPersistence;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgentPersistence;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguemodel.gameplayconfig.IGameConfigPersistence;
import com.dhl.g05.leaguemodel.league.ILeagueModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.IPlayerModelPersistence;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.ITeamModelPersistence;
import com.dhl.g05.leaguemodel.team.TeamModel;
import com.dhl.g05.simulation.Date;

public class DbPersistanceMock implements IGameConfigPersistence,IFreeAgentPersistence,ICoachLoad,IDatePersistence,IConferenceModelPersistence,IPlayerModelPersistence,IDivisionModelPersistence,ILeagueModelPersistence,ITeamModelPersistence,ICoachModelPersistence{

	@Override
	public ArrayList<HashMap<String, Object>> loadDetails() {
		ArrayList<HashMap<String,Object>> leagueNames = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> league = new HashMap<String,Object>();
		league.put("league_name","HockeyLeague");
		leagueNames.add(league);
		league = new HashMap<String,Object>();
		league.put("league_name","CanadaLeague");
		leagueNames.add(league);
		return leagueNames;
	}

	@Override
	public int saveLeagueObject(LeagueModel leagueObject) {
		if(leagueObject.getLeagueName().equals("HockeyLeague")) {
			return 1;
		}
		return 0;
	}

	@Override
	public int saveConferenceObject(int leagueId,ConferenceModel object) {
		if(leagueId==1 && object.getConferenceName().equals("Western Conference")) {
			return 1;
		}
		return 0;
	}

	@Override
	public int saveDivisionObject(int conferenceId,DivisionModel object) {
		if(conferenceId==1 && object.getDivisionName().equals("Atlantic")) {
			return 1;
		}
		return 0;
	}

	@Override
	public int saveTeamObject(int divisionId, TeamModel object, CoachModel coachObject) {
		if(divisionId==1 && object.getTeamName().equals("Striker Six")) {
			return 1;
		}
		return 0;
	}

	@Override
	public int savePlayerObject(int teamId,PlayerModel object) {
		if(teamId==1 && object.getPlayerName().equals("Cristiano Ronaldo")) {
			return 1;
		}
		return 0;
	}

	@Override
	public int saveLeagueCoachObject(int league_id, CoachModel object) {
		return 1;
	}

	@Override
	public int loadLeagueObject(int leagueId,LeagueModel leagueObject) {
		if(leagueId == 1) {
			ArrayList<ConferenceModel> conference = new ArrayList<ConferenceModel>();
			conference.add(new ConferenceModel("Western Conference",null));
			leagueObject.setLeagueName("HockeyLeague");
			leagueObject.setConferenceDetails(conference);
			return 1;
		}
		return 0;
	}

	@Override
	public List<CoachModel> loadLeagueCoachObject(String leagueName) {
		JsonMockDataDb mock = new JsonMockDataDb();
		if(leagueName.equalsIgnoreCase("HockeyLeague")) {
			return mock.coachList;
		}
		return null;
	}

	@Override
	public int loadConferenceObject(int leagueId, ConferenceModel conferenceObject) {
		if(leagueId==1 && conferenceObject.getConferenceName().equals("Western Conference")) {
			ArrayList<DivisionModel> divisions = new ArrayList<>();
			divisions.add(new DivisionModel("Atlantic",null));
			conferenceObject.setDivisionDetails(divisions);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadDivisionObject(int conferenceId, DivisionModel divisionObject) {
		if(conferenceId==1 && divisionObject.getDivisionName().equals("Atlantic")) {
			ArrayList<TeamModel> teams = new ArrayList<>();
			teams.add(new TeamModel("Striker Six",null,null,null));
			divisionObject.setTeamDetails(teams);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject) {
		if(divisionId==1 && teamObject.getTeamName().equals("Striker Six")) {
			ArrayList<PlayerModel> players = new ArrayList<>();
			players.add(new PlayerModel("Cristiano Ronaldo",null,null,10,10,10,10,10));
			teamObject.setPlayerList(players);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadPlayerObject(int teamId, PlayerModel playerObject) {
		if(teamId==1 && playerObject.getPlayerName().equals("Cristiano Ronaldo")) {
			return 1;
		}
		return 0;
	}

	@Override
	public void loadDate(LeagueModel league, Date date) {
		if (league.getLeagueName().equalsIgnoreCase("HockeyLeague")){
			Date.getInstance().setMonth(1);
			Date.getInstance().setYear(1);
			Date.getInstance().setDaysSinceStatIncreaseCheck(1);
			Date.getInstance().setDaysUntilStatIncreaseCheck(1);
		}
	}

	@Override
	public void saveDate(LeagueModel league, Date date) {
		if (league.getLeagueName().equalsIgnoreCase("HockeyLeague")){

		}
	}

	@Override
	public int saveFreeAgentObject(int leagueId, FreeAgentModel freeAgent) {
		if(leagueId==1) {
			return 1;
		}
		return 0;
	}

	@Override
	public int saveGamePlayObject(int leagueId, GamePlayConfigModel gamePlayConfigModel) {
		if(leagueId==1) {
			return 1;
		}
		return 0;
	}

	@Override
	public int loadLeagueFromTeam(String teamName) {
		if(teamName.equals("Striker Six")) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<HashMap<String, Object>> loadAllTeamName() {
		List<HashMap<String,Object>> teamName = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> name = new HashMap<String, Object>();
		name.put("team_name", "Striker Six");
		teamName.add(name);
		name = new HashMap<String, Object>();
		name.put("team_name", "Rockers");
		teamName.add(name);
		return teamName;
	}

}

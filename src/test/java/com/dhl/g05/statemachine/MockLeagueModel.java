package com.dhl.g05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;

public class MockLeagueModel implements ILeagueModel{
	private LeagueObject league;
	
	@Override
	public LeagueObject getLeague() {
		return league;
	}
	
	@Override
	public void setLeague(LeagueObject league) {
		this.league = league;
	}

	@Override
	public LeagueObject createLeague(String league, ArrayList<ConferenceObject> conferencedetail,
			ArrayList<PlayerObject> agent) {
		return new LeagueObject(league, conferencedetail, agent);
	}

	@Override
	public ConferenceObject createConference(String conference, List<DivisionObject> divisiondetail) {
		return new ConferenceObject(conference,divisiondetail);
	}

	@Override
	public DivisionObject createDivision(String division, ArrayList<TeamObject> teamdetail) {
		return new DivisionObject(division, teamdetail);
	}

	@Override
	public TeamObject createTeam(String team, String coach, String manager, ArrayList<PlayerObject> players) {
		return new TeamObject(team,coach,manager,players);
	}

	@Override
	public PlayerObject createPlayer(String name, String position, Boolean captain) {
		return new PlayerObject(name,position,captain);
	}

	@Override
	public String validateLeague(LeagueObject league) {
		return "Success";
	}

	@Override
	public String validateConference(ConferenceObject conference) {
		return "Success";
	}

	@Override
	public String validateDivision(DivisionObject division) {
		return "Success";
	}

	@Override
	public String validateTeam(TeamObject team) {
		return "Success";
	}

	@Override
	public String validatePlayer(PlayerObject player) {
		return "Success";
	}

	@Override
	public boolean persistLeague() {
		return true;
	}
	
	@Override
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team) {
		return true;
	}

	@Override
	public boolean loadTeam(Map<String, Object> teamDetails) {
		return true;
	}

}

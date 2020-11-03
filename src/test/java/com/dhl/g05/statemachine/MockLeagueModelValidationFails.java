package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;

public class MockLeagueModelValidationFails implements ILeagueModel{
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
		return null;
	}

	@Override
	public ConferenceObject createConference(String conference, List<DivisionObject> divisiondetail) {
		return null;
	}

	@Override
	public DivisionObject createDivision(String division, ArrayList<TeamObject> teamdetail) {
		return null;
	}

	@Override
	public TeamObject createTeam(String team, String coach, String manager, ArrayList<PlayerObject> players) {
		return null;
	}

	@Override
	public PlayerObject createPlayer(String name, String position, Boolean captain) {
		return null;
	}

	@Override
	public String validateLeague(LeagueObject league) {
		return "fail";
	}

	@Override
	public String validateConference(ConferenceObject conference) {
		return "fail";
	}

	@Override
	public String validateDivision(DivisionObject division) {
		return "fail";
	}

	@Override
	public String validateTeam(TeamObject team) {
		return "fail";
	}

	@Override
	public String validatePlayer(PlayerObject player) {
		return "fail";
	}

	@Override
	public boolean persistLeague() {
		return false;
	}
	
	@Override
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team) {
		return false;
	}

	@Override
	public boolean loadTeam(String leagueName, String conference,String division,String team) {
		return false;
	}

}

package com.dhl.g05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leagueobjects.ConferenceObject;
import com.dhl.g05.leagueobjects.DivisionObject;
import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;
import com.dhl.g05.leagueobjects.TeamObject;

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
		return  new PlayerObject(name, name, captain);
	}

	@Override
	public boolean validateLeague(LeagueObject league) {
		return false;
	}

	@Override
	public boolean validateConference(ConferenceObject conference) {
		return false;
	}

	@Override
	public boolean validateDivision(DivisionObject division) {
		return false;
	}

	@Override
	public boolean validateTeam(TeamObject team) {
		return false;
	}

	@Override
	public boolean validatePlayer(PlayerObject player) {
		return false;
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
	public boolean loadTeam(Map<String, Object> teamDetails) {
		return false;
	}

}

package com.dhl.g05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leagueobjects.ConferenceObject;
import com.dhl.g05.leagueobjects.DivisionObject;
import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;
import com.dhl.g05.leagueobjects.TeamObject;

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
	public PlayerObject createPlayer(Map<String, Object> players) {
		return  new PlayerObject(players);
	}

	@Override
	public boolean validateLeague(LeagueObject league) {
		return true;
	}

	@Override
	public boolean validateConference(ConferenceObject conference) {
		return true;
	}

	@Override
	public boolean validateDivision(DivisionObject division) {
		return true;
	}

	@Override
	public boolean validateTeam(TeamObject team) {
		return true;
	}

	@Override
	public boolean validatePlayer(PlayerObject player) {
		return true;
	}

	@Override
	public boolean persistLeague() {
		return true;
	}
	
	@Override
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team) {
		return true;
	}

}

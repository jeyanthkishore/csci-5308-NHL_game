package com.dhl.g05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.operation.OperationModel;

public class LeagueModel implements ILeagueModel{
	public LeagueObject league;

	
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
		return new ConferenceObject(conference, divisiondetail);
	}

	@Override
	public DivisionObject createDivision(String division, ArrayList<TeamObject> teamdetail) {
		return new DivisionObject(division, teamdetail);
	}

	@Override
	public TeamObject createTeam(String team, String coach, String manager, ArrayList<PlayerObject> players) {
		return new TeamObject(team, coach, manager, players);
	}

	@Override
	public PlayerObject createPlayer(String name,String position, Boolean captain) {
		return new PlayerObject(name,position,captain);
	}

	@Override
	public String validateLeague(LeagueObject league) {
		return league.getResult();
	}

	@Override
	public String validateConference(ConferenceObject conference) {
		return conference.getResult();
	}

	@Override
	public String validateDivision(DivisionObject division) {
		return division.getResult();
	}

	@Override
	public String validateTeam(TeamObject team) {
		return team.getResult();
	}

	@Override
	public String validatePlayer(PlayerObject player) {
		return player.getResult();
	}

	@Override
	public boolean persistLeague() {
		//TODO:
		return false;
	}

	@Override
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team) {
		//TODO:
		return false;
	}

	@Override
	public boolean loadTeam(Map<String, Object> teamDetails) {
		

		//load league object and save to variable
		return false;
	}

	

}

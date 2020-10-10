package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.operation.IDataBasePersistence;
import com.dhl.g05.operation.OperationModel;

public class LeagueModel implements ILeagueModel{
	public LeagueObject league;
	public IDataBasePersistence database;
	public OperationModel operation;

	public LeagueModel(IDataBasePersistence database) {
		this.database = database;
	}
	
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
		return new LeagueObject(league, conferencedetail, agent, database);
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
		operation = new OperationModel(league,database);
		String result = operation.getResult();
		return result.equalsIgnoreCase("Success"); //TODO: change to boolean
	}

	@Override
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team) {
		List<ConferenceObject> conferences = league.getConferenceDetails();
		for (ConferenceObject c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionObject> divisions = c.getDivisionDetails();
				for (DivisionObject d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						d.getTeamDetails().add(team);
						return true;
					}
				}
			}
		}
		return false;
	}
	/*
	public void loadNewTeam() {
		operation = new OperationModel(database);
		ArrayList<HashMap<String,Object>> teams = new ArrayList<HashMap<String,Object>>();
		teams = operation.getNewListTeam();
	}*/

	@Override
	public boolean loadTeam(String leagueName,String conference,String division,String team) {
		
		operation = new OperationModel(leagueName, conference, division, team, database);
		league = operation.getLeagueObject();
		if (league.checkLeaguePresent()) {
			return true;
		} else {
			league = null;
			return false;
		}
	}


}

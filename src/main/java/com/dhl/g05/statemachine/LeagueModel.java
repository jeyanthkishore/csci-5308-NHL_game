package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.operation.IDataBasePersistence;
import com.dhl.g05.operation.OperationModel;

public class LeagueModel implements ILeagueModel{
	private LeagueObject league;
	private TeamObject currentTeam;
	private IDataBasePersistence database;

	public LeagueModel(IDataBasePersistence database) {
		this.database = database;
	}
	
	public LeagueObject createLeague(String leagueName, List<ConferenceObject> conferences, List<PlayerObject> freeAgents) {
		return new LeagueObject(leagueName, conferences, freeAgents, database);
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
	public boolean persistLeague() { 
		try {
			//TODO: change when operation model updated, currently saves league to db
			OperationModel operation = new OperationModel(league,database);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamObject team) {
		if (league == null) {
			return false;
		}
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

	@Override
	public boolean loadTeam(String team) {
		try {
			//TODO: change when operation model updated // currently does not load a team
			OperationModel operation = new OperationModel(team, database);
			this.league = operation.getLeagueObject();
			this.currentTeam = this.getTeamFromLeagueObject(this.league, team);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	
	public TeamObject getTeamFromLeagueObject(LeagueObject leagueObject, String teamName) {
		TeamObject team = null;
		List<ConferenceObject> conferences = league.getConferenceDetails();
		for (ConferenceObject c: conferences) {
			List<DivisionObject> divisions = c.getDivisionDetails();
			for (DivisionObject d: divisions) {
				List<TeamObject> teams = d.getTeamDetails();
				for (TeamObject t: teams) {
					if (t.getTeamName().equalsIgnoreCase(teamName)) {
						team = t;
					}
				}
				
			}
			
		}
		return team;
	}

	@Override
	public TeamObject getCurrentTeam() {
		return currentTeam;
	}

	@Override
	public String validateLeague(LeagueObject league) {
		return league.validate();
	}

	@Override
	public String validateConference(ConferenceObject conference) {
		return conference.validate();
	}

	@Override
	public String validateDivision(DivisionObject division) {
		return division.validate();
	}

	@Override
	public String validateTeam(TeamObject team) {
		return team.validate();
	}

	@Override
	public String validatePlayer(PlayerObject player) {
		return player.validate();
	}


}

package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.operation.IDataBasePersistence;
import com.dhl.g05.simulation.Date;

public class LeagueModel implements ILeagueModel{
	private LeagueObject league;
	private TeamObject currentTeam;
	private IDataBasePersistence database;

	public LeagueModel(IDataBasePersistence database) {
		this.database = database;
	}

	public LeagueObject createLeague(String leagueName, List<ConferenceObject> conferences, List<FreeAgentObject> freeAgents, List<CoachObject> coaches) {
		return new LeagueObject(leagueName, conferences, freeAgents, coaches , database);
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
		
		Date.getInstance().saveDate(getLeague(), database);
		
		int leagueId = this.league.saveLeagueObject(database);
		if (leagueId == 0) {
			return false;
		}
		for (ConferenceObject c: league.getConferenceDetails()) {
			int conferenceId = c.saveConferenceObject(leagueId, database);
			if (conferenceId == 0) {
				return false;
			}
			for (DivisionObject d: c.getDivisionDetails()) {
				int divisionId = d.saveDivisionObject(conferenceId, database);
				if (divisionId == 0) {
					return false;
				}
				for (TeamObject t: d.getTeamDetails()) {
					int teamId = t.saveTeamObject(divisionId, database);
					if (teamId == 0) {
						return false;
					}
					for (PlayerObject p: t.getPlayerList()) {
						int playerId = p.savePlayerObject(teamId, database);
						if (playerId == 0) {
							return false;
						}
					}
				}
			}
		}
		return true;
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
						currentTeam = team;
						return true;
					}
				}
			}
		}

		return false;
	}

	
	@Override
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
		
		TeamObject teamToLoad = null;
		LeagueObject newLeague = new LeagueObject();
		int leagueId = newLeague.loadLeagueObject(leagueName, database);
		if (leagueId == 0) {
			return false;
		}
		
		List<ConferenceObject> conferences = newLeague.getConferenceDetails();
		if (conferences == null) {
			return false;
		}
		
		for (ConferenceObject c: conferences) {
			int conferenceId = c.loadConferenceObject(leagueId, database);
			String cName = c.getConferenceName();
			
			if (conferenceId == 0) {
				return false;
			}
			
			List<DivisionObject> divisions = c.getDivisionDetails();
			if (divisions == null) {
				return false;
			}
			
			for (DivisionObject d: divisions) {
				int divisionId = d.loadDivisionObject(conferenceId, database);
				String dName = d.getDivisionName();
				
				if (divisionId == 0) {
					return false;
				}
				
				List<TeamObject> teams = d.getTeamDetails();
				if (teams == null) {
					return false;
				}
				
				for (TeamObject t: teams) {
					int teamId = t.loadTeamObject(divisionId, database);
					String tName = t.getTeamName();
					
					if (teamId == 0) {
						return false;
					}
					
					List<PlayerObject> players = t.getPlayerList();
					if (players == null) {
						return false;
					}
					
					for (PlayerObject p: players) {
						int playerId = p.loadPlayerObject(teamId, database);
						if (playerId == 0) {
							return false;
						}
					}
					
					if (tName.equalsIgnoreCase(teamName) && dName.equalsIgnoreCase(divisionName) && cName.equalsIgnoreCase(conferenceName)) {
						teamToLoad = t;
					}
				}
			}
		}
		
		if (teamToLoad != null) {
			currentTeam = teamToLoad;
			this.league = newLeague;
			Date.getInstance().loadDate(newLeague, database);
			return true;
		}

		return false;
	}
	

	public TeamObject getTeamFromLeagueObject(LeagueObject leagueObject, String conferenceName, String divisionName, String teamName) {
		if (league == null) {
			return null;
		}
		
		TeamObject team = null;
		
		List<ConferenceObject> conferences = league.getConferenceDetails();
		for (ConferenceObject c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionObject> divisions = c.getDivisionDetails();
				for (DivisionObject d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						List<TeamObject> teams = d.getTeamDetails();
						for (TeamObject t: teams) {
							if (t.getTeamName().equalsIgnoreCase(teamName)) {
								team = t;
							}
						}
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

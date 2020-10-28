package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.ValidateEnumModel;
import com.dhl.g05.leaguemodel.coach.CoachObject;
import com.dhl.g05.leaguemodel.conference.ConferenceObject;
import com.dhl.g05.leaguemodel.conference.IConferenceModelPersistence;
import com.dhl.g05.leaguemodel.division.DivisionObject;
import com.dhl.g05.leaguemodel.division.IDivisionModelPersistence;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentObject;
import com.dhl.g05.leaguemodel.league.ILeagueModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.leaguemodel.manager.ManagerObject;
import com.dhl.g05.leaguemodel.player.IPlayerModelPersistence;
import com.dhl.g05.leaguemodel.player.PlayerObject;
import com.dhl.g05.leaguemodel.team.ITeamModelPersistence;
import com.dhl.g05.leaguemodel.team.TeamObject;
import com.dhl.g05.operation.ConferencePersistence;
import com.dhl.g05.operation.DatePersistence;
import com.dhl.g05.operation.DivisionPersistence;
import com.dhl.g05.operation.IDatePersistence;
import com.dhl.g05.operation.LeaguePersistence;
import com.dhl.g05.operation.PlayerPersistence;
import com.dhl.g05.operation.TeamPersistence;
import com.dhl.g05.simulation.Date;

public class LeagueModel implements ILeagueModel{
	private LeagueObject league;
	private TeamObject currentTeam;
	private ILeagueModelPersistence leagueDatabase;
	private IConferenceModelPersistence conferenceDatabase;
	private IDivisionModelPersistence divisionDatabase;
	private ITeamModelPersistence teamDatabase;
	private IPlayerModelPersistence playerDatabase;
	private IDatePersistence dateDatabase;

	public LeagueModel() {
		this.leagueDatabase = new LeaguePersistence();
		this.conferenceDatabase = new ConferencePersistence();
		this.divisionDatabase = new DivisionPersistence();
		this.teamDatabase = new TeamPersistence();
		this.playerDatabase = new PlayerPersistence();
		this.dateDatabase = new DatePersistence();
	}

	public void setDateDatabase(IDatePersistence dateDatabase) {
		this.dateDatabase = dateDatabase;
	}

	public void setLeagueDatabase(ILeagueModelPersistence leagueDatabase) {
		this.leagueDatabase = leagueDatabase;
	}

	public void setConferenceDatabase(IConferenceModelPersistence conferenceDatabase) {
		this.conferenceDatabase = conferenceDatabase;
	}

	public void setDivisionDatabase(IDivisionModelPersistence divisionDatabase) {
		this.divisionDatabase = divisionDatabase;
	}

	public void setTeamDatabase(ITeamModelPersistence teamDatabase) {
		this.teamDatabase = teamDatabase;
	}

	public void setPlayerDatabase(IPlayerModelPersistence playerDatabase) {
		this.playerDatabase = playerDatabase;
	}

	public LeagueObject createLeague(String leagueName, List<ConferenceObject> conferences, List<FreeAgentObject> freeAgents, List<CoachObject> coaches) {
		return new LeagueObject(leagueName, conferences, freeAgents, coaches , leagueDatabase);
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
		
		Date.getInstance().saveDate(getLeague(), dateDatabase);
		
		int leagueId = this.league.saveLeagueObject(leagueDatabase);
		if (leagueId == 0) {
			return false;
		}
		for (ConferenceObject c: league.getConferenceDetails()) {
			int conferenceId = c.saveConferenceObject(leagueId, conferenceDatabase);
			if (conferenceId == 0) {
				return false;
			}
			for (DivisionObject d: c.getDivisionDetails()) {
				int divisionId = d.saveDivisionObject(conferenceId, divisionDatabase);
				if (divisionId == 0) {
					return false;
				}
				for (TeamObject t: d.getTeamDetails()) {
					int teamId = t.saveTeamObject(divisionId, teamDatabase);
					if (teamId == 0) {
						return false;
					}
					for (PlayerObject p: t.getPlayerList()) {
						int playerId = p.savePlayerObject(teamId, playerDatabase);
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
		int leagueId = newLeague.loadLeagueObject(leagueName, leagueDatabase);
		if (leagueId == 0) {
			return false;
		}
		
		List<ConferenceObject> conferences = newLeague.getConferenceDetails();
		if (conferences == null) {
			return false;
		}
		
		for (ConferenceObject c: conferences) {
			int conferenceId = c.loadConferenceObject(leagueId, conferenceDatabase);
			String cName = c.getConferenceName();
			
			if (conferenceId == 0) {
				return false;
			}
			
			List<DivisionObject> divisions = c.getDivisionDetails();
			if (divisions == null) {
				return false;
			}
			
			for (DivisionObject d: divisions) {
				int divisionId = d.loadDivisionObject(conferenceId, divisionDatabase);
				String dName = d.getDivisionName();
				
				if (divisionId == 0) {
					return false;
				}
				
				List<TeamObject> teams = d.getTeamDetails();
				if (teams == null) {
					return false;
				}
				
				for (TeamObject t: teams) {
					int teamId = t.loadTeamObject(divisionId, teamDatabase);
					String tName = t.getTeamName();
					
					if (teamId == 0) {
						return false;
					}
					
					List<PlayerObject> players = t.getPlayerList();
					if (players == null) {
						return false;
					}
					
					for (PlayerObject p: players) {
						int playerId = p.loadPlayerObject(teamId, playerDatabase);
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
			Date.getInstance().loadDate(newLeague, dateDatabase);
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
	public ValidateEnumModel validateLeague(LeagueObject league) {
		return league.validate();
	}

	@Override
	public ValidateEnumModel validateConference(ConferenceObject conference) {
		return conference.validate();
	}

	@Override
	public  ValidateEnumModel validateManager(ManagerObject managerObject) {
			return managerObject.validate();
	}

	@Override
	public ValidateEnumModel validateDivision(DivisionObject division) {
		return division.validate();
	}

	@Override
	public ValidateEnumModel validateTeam(TeamObject team) {
		return team.validate();
	}

	@Override
	public ValidateEnumModel validatePlayer(PlayerObject player) {
		return player.validate();
	}


}

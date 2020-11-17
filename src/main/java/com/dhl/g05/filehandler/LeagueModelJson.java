package com.dhl.g05.filehandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.coach.ICoachModelPersistence;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConferenceModelPersistence;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivisionModelPersistence;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgentPersistence;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.IGameConfigPersistence;
import com.dhl.g05.league.ILeagueModelPersistence;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayerModelPersistence;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.ITeamModelPersistence;
import com.dhl.g05.team.TeamModel;

public class LeagueModelJson implements ILeagueModelJson{
	private LeagueModel league;
	private TeamModel currentTeam;
	private ILeagueModelPersistence leagueDatabase;
	private IConferenceModelPersistence conferenceDatabase;
	private IDivisionModelPersistence divisionDatabase;
	private ITeamModelPersistence teamDatabase;
	private IPlayerModelPersistence playerDatabase;
	private IFreeAgentPersistence freeAgentDatabase;
	private ICoachModelPersistence coachDataBase;
	private IGameConfigPersistence gamePlayDatabase;

	public LeagueModelJson() {
		this.leagueDatabase = AbstractDataBaseFactory.getFactory().getLeagueDatabase();
		this.conferenceDatabase = AbstractDataBaseFactory.getFactory().getConferenceDatabase();
		this.divisionDatabase = AbstractDataBaseFactory.getFactory().getDivisionDatabase();
		this.teamDatabase = AbstractDataBaseFactory.getFactory().getTeamDatabase();
		this.playerDatabase = AbstractDataBaseFactory.getFactory().getPlayerDatabase();
		this.freeAgentDatabase = AbstractDataBaseFactory.getFactory().getFreeAgentDatabase();
		this.coachDataBase = AbstractDataBaseFactory.getFactory().getCoachDatabase();
		this.gamePlayDatabase = AbstractDataBaseFactory.getFactory().getGameConfigDatabase();
	}

	public void setFreeAgentDatabase(IFreeAgentPersistence freeAgentDatabase) {
		this.freeAgentDatabase = freeAgentDatabase;
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
	
	public void setCoachDatabase(ICoachModelPersistence coachDataBase) {
		this.coachDataBase = coachDataBase;
	}
	
	public void setGamePlayDatabase(IGameConfigPersistence gamePlayDatabase) {
		this.gamePlayDatabase = gamePlayDatabase;
	}

	@Override
	public LeagueModel getLeague() {
		return league;
	}

	@Override
	public void setLeague(LeagueModel league) {
		this.league = league;
	}

	@Override
	public boolean persistLeague() { 

		int leagueId = this.league.saveLeagueObject(leagueDatabase);
		if (leagueId == 0) {
			return false;
		}
		for (ConferenceModel c: league.getConferenceDetails()) {
			int conferenceId = c.saveConferenceObject(leagueId, conferenceDatabase);
			if (conferenceId == 0) {
				return false;
			}
			for (DivisionModel d: c.getDivisionDetails()) {
				int divisionId = d.saveDivisionObject(conferenceId, divisionDatabase);
				if (divisionId == 0) {
					return false;
				}
				for (TeamModel t: d.getTeamDetails()) {
					int teamId = t.saveTeamObject(divisionId, teamDatabase);
					if (teamId == 0) {
						return false;
					}
					for (PlayerModel p: t.getPlayerList()) {
						int playerId = p.savePlayerObject(teamId, playerDatabase);
						if (playerId == 0) {
							return false;
						}
					}
				}
			}
		}
		
		for (FreeAgentModel f : league.getFreeAgent()) {
			int freeAgentId = f.saveFreeAgentObject(leagueId, freeAgentDatabase);
			if(freeAgentId == 0) {
				return false;
			}
		}
		for(CoachModel co : league.getFreeCoach()) {
			int coachId = co.saveLeagueCoachObject(leagueId, coachDataBase);
			if(coachId == 0) {
				return false;
			}
		}
		GamePlayConfigModel gamePlay = league.getGamePlayConfig();
		int gamePlayId = gamePlay.saveGamePlayObject(leagueId,gamePlayDatabase);
		if(gamePlayId == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team) {
		if (league == null) {
			return false;
		}
		List<ConferenceModel> conferences = league.getConferenceDetails();
		for (ConferenceModel c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionModel> divisions = c.getDivisionDetails();
				for (DivisionModel d: divisions) {
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
	public boolean loadTeam(String teamName) {

		TeamModel teamToLoad = null;
		LeagueModel newLeague = new LeagueModel();
		int leagueId = newLeague.loadLeagueFromTeam(teamName,leagueDatabase);
		if (leagueId == 0) {
			return false;
		}
		leagueId = newLeague.loadLeagueObject(leagueId, leagueDatabase);
		if (leagueId == 0) {
			return false;
		}

		List<ConferenceModel> conferences = newLeague.getConferenceDetails();
		if (conferences == null) {
			return false;
		}

		for (ConferenceModel c: conferences) {
			int conferenceId = c.loadConferenceObject(leagueId, conferenceDatabase);

			if (conferenceId == 0) {
				return false;
			}

			List<DivisionModel> divisions = new ArrayList<DivisionModel>();
			divisions =	c.getDivisionDetails();
			if (divisions == null) {
				return false;
			}

			for (DivisionModel d: divisions) {
				int divisionId = d.loadDivisionObject(conferenceId, divisionDatabase);

				if (divisionId == 0) {
					return false;
				}

				List<TeamModel> teams = new ArrayList<TeamModel>();
				teams =	d.getTeamDetails();
				if (teams == null) {
					return false;
				}

				for (TeamModel t: teams) {
					int teamId = t.loadTeamObject(divisionId, teamDatabase);
					String tName = t.getTeamName();

					if (teamId == 0) {
						return false;
					}

					List<PlayerModel> players = new ArrayList<PlayerModel>();
					players = t.getPlayerList();
					if (players == null) {
						return false;
					}

					for (PlayerModel p: players) {
						int playerId = p.loadPlayerObject(teamId, playerDatabase);
						if (playerId == 0) {
							return false;
						}
					}

					if (tName.equalsIgnoreCase(teamName)) {
						teamToLoad = t;
					}
				}
			}
		}

		if (teamToLoad != null) {
			currentTeam = teamToLoad;
			this.league = newLeague;
			return true;
		}

		return false;
	}


	public TeamModel getTeamFromLeagueObject(LeagueModel leagueObject, String conferenceName, String divisionName, String teamName) {
		if (league == null) {
			return null;
		}

		TeamModel team = null;

		List<ConferenceModel> conferences = league.getConferenceDetails();
		for (ConferenceModel c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionModel> divisions = c.getDivisionDetails();
				for (DivisionModel d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						List<TeamModel> teams = d.getTeamDetails();
						for (TeamModel t: teams) {
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
	public TeamModel getCurrentTeam() {
		return currentTeam;
	}

}

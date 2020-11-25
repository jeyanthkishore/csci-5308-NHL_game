package com.dhl.g05.filehandler;

public class LeagueModelJson implements ILeagueModelJson{
//	private LeagueModel league;
//	private ITeam currentTeam;
//	private ILeagueModelPersistence leagueDatabase;
//	private IConferenceModelPersistence conferenceDatabase;
//	private IDivisionModelPersistence divisionDatabase;
//	private ITeamModelPersistence teamDatabase;
//	private IPlayerModelPersistence playerDatabase;
//	private IFreeAgentPersistence freeAgentDatabase;
//	private ICoachModelPersistence coachDataBase;
//	private IGameConfigPersistence gamePlayDatabase;
//
//	public LeagueModelJson() {
//		this.leagueDatabase = AbstractDataBaseFactory.getFactory().getLeagueDatabase();
//		this.conferenceDatabase = AbstractDataBaseFactory.getFactory().getConferenceDatabase();
//		this.divisionDatabase = AbstractDataBaseFactory.getFactory().getDivisionDatabase();
//		this.teamDatabase = AbstractDataBaseFactory.getFactory().getTeamDatabase();
//		this.playerDatabase = AbstractDataBaseFactory.getFactory().getPlayerDatabase();
//		this.freeAgentDatabase = AbstractDataBaseFactory.getFactory().getFreeAgentDatabase();
//		this.coachDataBase = AbstractDataBaseFactory.getFactory().getCoachDatabase();
//		this.gamePlayDatabase = AbstractDataBaseFactory.getFactory().getGameConfigDatabase();
//	}
//
//	public void setFreeAgentDatabase(IFreeAgentPersistence freeAgentDatabase) {
//		this.freeAgentDatabase = freeAgentDatabase;
//	}
//
//	public void setLeagueDatabase(ILeagueModelPersistence leagueDatabase) {
//		this.leagueDatabase = leagueDatabase;
//	}
//
//	public void setConferenceDatabase(IConferenceModelPersistence conferenceDatabase) {
//		this.conferenceDatabase = conferenceDatabase;
//	}
//
//	public void setDivisionDatabase(IDivisionModelPersistence divisionDatabase) {
//		this.divisionDatabase = divisionDatabase;
//	}
//
//	public void setTeamDatabase(ITeamModelPersistence teamDatabase) {
//		this.teamDatabase = teamDatabase;
//	}
//
//	public void setPlayerDatabase(IPlayerModelPersistence playerDatabase) {
//		this.playerDatabase = playerDatabase;
//	}
//	
//	public void setCoachDatabase(ICoachModelPersistence coachDataBase) {
//		this.coachDataBase = coachDataBase;
//	}
//	
//	public void setGamePlayDatabase(IGameConfigPersistence gamePlayDatabase) {
//		this.gamePlayDatabase = gamePlayDatabase;
//	}
//
//	@Override
//	public LeagueModel getLeague() {
//		return league;
//	}
//
//	@Override
//	public void setLeague(LeagueModel league) {
//		this.league = league;
//	}
//
//	@Override
//	public boolean persistLeague() { 
//
//		int leagueId = 0;
//		if (leagueId == 0) {
//			return false;
//		}
//		for (IConference c: league.getConferenceDetails()) {
//			int conferenceId = c.saveConferenceObject(leagueId, conferenceDatabase);
//			if (conferenceId == 0) {
//				return false;
//			}
//			for (IDivision d: c.getDivisionDetails()) {
//				int divisionId = d.saveDivisionObject(conferenceId, divisionDatabase);
//				if (divisionId == 0) {
//					return false;
//				}
//				for (ITeam t: d.getTeamDetails()) {
//					int teamId = t.saveTeamObject(divisionId, teamDatabase);
//					if (teamId == 0) {
//						return false;
//					}
//					for (IPlayer p: t.getPlayerList()) {
//						int playerId = p.savePlayerObject(teamId, playerDatabase);
//						if (playerId == 0) {
//							return false;
//						}
//					}
//				}
//			}
//		}
//		
//		for (IFreeAgent f : league.getFreeAgent()) {
//			int freeAgentId = f.saveFreeAgentObject(leagueId, freeAgentDatabase);
//			if(freeAgentId == 0) {
//				return false;
//			}
//		}
//		for(ICoach co : league.getFreeCoach()) {
//			int coachId = co.saveLeagueCoachObject(leagueId, coachDataBase);
//			if(coachId == 0) {
//				return false;
//			}
//		}
//		GamePlayConfigModel gamePlay = league.getGamePlayConfig();
//		int gamePlayId = gamePlay.saveGamePlayObject(leagueId,gamePlayDatabase);
//		if(gamePlayId == 0) {
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team) {
//		if (league == null) {
//			return false;
//		}
//		List<IConference> conferences = league.getConferenceDetails();
//		for (IConference c: conferences) {
//			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
//				List<IDivision> divisions = c.getDivisionDetails();
//				for (IDivision d: divisions) {
//					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
//						d.getTeamDetails().add(team);
//						currentTeam = team;
//						return true;
//					}
//				}
//			}
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean loadTeam(String teamName) {
//
//		ITeam teamToLoad = null;
//		LeagueModel newLeague = new LeagueModel();
//		int leagueId = newLeague.loadLeagueFromTeam(teamName,leagueDatabase);
//		if (leagueId == 0) {
//			return false;
//		}
//		leagueId = newLeague.loadLeagueObject(leagueId, leagueDatabase);
//		if (leagueId == 0) {
//			return false;
//		}
//
//		List<IConference> conferences = newLeague.getConferenceDetails();
//		if (conferences == null) {
//			return false;
//		}
//
//		for (IConference c: conferences) {
//			int conferenceId = c.loadConferenceObject(leagueId, conferenceDatabase);
//
//			if (conferenceId == 0) {
//				return false;
//			}
//
//			List<IDivision> divisions = new ArrayList<>();
//			divisions =	c.getDivisionDetails();
//			if (divisions == null) {
//				return false;
//			}
//
//			for (IDivision d: divisions) {
//				int divisionId = d.loadDivisionObject(conferenceId, divisionDatabase);
//
//				if (divisionId == 0) {
//					return false;
//				}
//
//				List<ITeam> teams = new ArrayList<>();
//				teams =	d.getTeamDetails();
//				if (teams == null) {
//					return false;
//				}
//
//				for (ITeam t: teams) {
//					int teamId = t.loadTeamObject(divisionId, teamDatabase);
//					String tName = t.getTeamName();
//
//					if (teamId == 0) {
//						return false;
//					}
//
//					List<IPlayer> players = new ArrayList<>();
//					players = t.getPlayerList();
//					if (players == null) {
//						return false;
//					}
//
//					for (IPlayer p: players) {
//						int playerId = p.loadPlayerObject(teamId, playerDatabase);
//						if (playerId == 0) {
//							return false;
//						}
//					}
//
//					if (tName.equalsIgnoreCase(teamName)) {
//						teamToLoad = t;
//					}
//				}
//			}
//		}
//
//		if (teamToLoad != null) {
//			currentTeam = teamToLoad;
//			this.league = newLeague;
//			return true;
//		}
//
//		return false;
//	}
//
//
//	public ITeam getTeamFromLeagueObject(LeagueModel leagueObject, String conferenceName, String divisionName, String teamName) {
//		if (league == null) {
//			return null;
//		}
//
//		ITeam team = null;
//
//		List<IConference> conferences = league.getConferenceDetails();
//		for (IConference c: conferences) {
//			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
//				List<IDivision> divisions = c.getDivisionDetails();
//				for (IDivision d: divisions) {
//					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
//						List<ITeam> teams = d.getTeamDetails();
//						for (ITeam t: teams) {
//							if (t.getTeamName().equalsIgnoreCase(teamName)) {
//								team = t;
//							}
//						}
//					}
//				}
//			}
//		}
//
//		return team;
//	}
//
//	@Override
//	public ITeam getCurrentTeam() {
//		return currentTeam;
//	}
//
}

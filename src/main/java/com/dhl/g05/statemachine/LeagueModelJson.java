package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.coach.CoachPersistence;
import com.dhl.g05.leaguemodel.coach.ICoachModelPersistence;
import com.dhl.g05.leaguemodel.conference.ConferenceConstant;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.conference.ConferencePersistence;
import com.dhl.g05.leaguemodel.conference.IConferenceModelPersistence;
import com.dhl.g05.leaguemodel.division.DivisionConstant;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.division.DivisionPersistence;
import com.dhl.g05.leaguemodel.division.IDivisionModelPersistence;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentPersistence;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgentPersistence;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayPersistence;
import com.dhl.g05.leaguemodel.gameplayconfig.IGameConfigPersistence;
import com.dhl.g05.leaguemodel.league.ILeagueModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueConstant;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.league.LeaguePersistence;
import com.dhl.g05.leaguemodel.player.IPlayerModelPersistence;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.player.PlayerPersistence;
import com.dhl.g05.leaguemodel.team.ITeamModelPersistence;
import com.dhl.g05.leaguemodel.team.TeamConstant;
import com.dhl.g05.leaguemodel.team.TeamModel;
import com.dhl.g05.leaguemodel.team.TeamPersistence;
import com.dhl.g05.operation.DatePersistence;
import com.dhl.g05.operation.IDatePersistence;
import com.dhl.g05.simulation.Date;

public class LeagueModelJson implements ILeagueModelJson{
	private LeagueModel league;
	private TeamModel currentTeam;
	private ILeagueModelPersistence leagueDatabase;
	private IConferenceModelPersistence conferenceDatabase;
	private IDivisionModelPersistence divisionDatabase;
	private ITeamModelPersistence teamDatabase;
	private IPlayerModelPersistence playerDatabase;
	private IDatePersistence dateDatabase;
	private IFreeAgentPersistence freeAgentDatabase;
	private ICoachModelPersistence coachDataBase;
	private IGameConfigPersistence gamePlayDatabase;

	public LeagueModelJson() {
		this.leagueDatabase = new LeaguePersistence();
		this.conferenceDatabase = new ConferencePersistence();
		this.divisionDatabase = new DivisionPersistence();
		this.teamDatabase = new TeamPersistence();
		this.playerDatabase = new PlayerPersistence();
		this.dateDatabase = new DatePersistence();
		this.freeAgentDatabase = new FreeAgentPersistence();
		this.coachDataBase = new CoachPersistence();
		this.gamePlayDatabase = new GamePlayPersistence();
	}

	public void setDateDatabase(IDatePersistence dateDatabase) {
		this.dateDatabase = dateDatabase;
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

	public LeagueModel createLeague(String leagueName, List<ConferenceModel> conferences, List<FreeAgentModel> freeAgents, List<CoachModel> coaches, List<String> managers,GamePlayConfigModel gamePlay) {
		return new LeagueModel(leagueName, conferences, freeAgents, coaches, managers, gamePlay,leagueDatabase);
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

		Date.getInstance().saveDate(getLeague(), dateDatabase);

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
	public Boolean checkTeamNotUnique(String teamName) {
		TeamModel teamObject = new TeamModel();
		List<HashMap<String,Object>> teamNameList = teamObject.loadAllTeamName(teamDatabase);
		for(HashMap<String,Object> team : teamNameList) {
			if(team.get("team_name").equals(teamName)){
				return true;
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

	@Override
	public LeagueConstant validateLeague(LeagueModel league) {
		return league.validate();
	}

	@Override
	public ConferenceConstant validateConference(ConferenceModel conference) {
		return conference.validate();
	}

	@Override
	public DivisionConstant validateDivision(DivisionModel division) {
		return division.validate();
	}

	@Override
	public TeamConstant validateTeam(TeamModel team) {
		return team.validate();
	}

	@Override
	public FreeAgentConstant validatePlayer(PlayerModel player) {
		return player.validate();
	}

}

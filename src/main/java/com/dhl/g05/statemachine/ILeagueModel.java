package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.ValidateEnumModel;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.manager.ManagerModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;


public interface ILeagueModel {
	public LeagueModel getLeague();
	public TeamModel getCurrentTeam();
	public LeagueModel createLeague(String leagueName, List<ConferenceModel> conferences, List<FreeAgentModel> freeAgents, List<CoachModel> coaches);
	public void setLeague(LeagueModel league);
	public boolean persistLeague();
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team);
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName);
	public ValidateEnumModel validateLeague(LeagueModel league);
	public ValidateEnumModel validateConference(ConferenceModel conference);
	public ValidateEnumModel validateDivision(DivisionModel division);
	public ValidateEnumModel validateTeam(TeamModel team);
	public ValidateEnumModel validatePlayer(PlayerModel player);
	public ValidateEnumModel validateManager(ManagerModel managerObject);

}

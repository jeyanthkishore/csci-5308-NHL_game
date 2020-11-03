package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.conference.ConferenceConstant;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionConstant;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.freeagent.FreeAgentConstant;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.league.LeagueConstant;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamConstant;
import com.dhl.g05.team.TeamModel;


public interface ILeagueModelJson {
	
	public LeagueModel getLeague();
	public TeamModel getCurrentTeam();
	public LeagueModel createLeague(String leagueName, List<ConferenceModel> conferences, List<FreeAgentModel> freeAgents, List<CoachModel> coaches, List<String> managers,GamePlayConfigModel gamePlayConfig);
	public void setLeague(LeagueModel league);
	public boolean persistLeague();
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team);
	public boolean loadTeam(String teamName);
	public LeagueConstant validateLeague(LeagueModel league);
	public ConferenceConstant validateConference(ConferenceModel conference);
	public DivisionConstant validateDivision(DivisionModel division);
	public TeamConstant validateTeam(TeamModel team);
	public FreeAgentConstant validatePlayer(PlayerModel player);
	public Boolean checkTeamNotUnique(String teamName);

}

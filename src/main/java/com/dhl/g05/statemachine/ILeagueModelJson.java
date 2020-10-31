package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceConstant;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionConstant;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguemodel.league.LeagueConstant;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamConstant;
import com.dhl.g05.leaguemodel.team.TeamModel;


public interface ILeagueModelJson {
	
	public LeagueModel getLeague();
	public TeamModel getCurrentTeam();
	public LeagueModel createLeague(String leagueName, List<ConferenceModel> conferences, List<FreeAgentModel> freeAgents, List<CoachModel> coaches, List<String> managers,GamePlayConfigModel gamePlayConfig);
	public void setLeague(LeagueModel league);
	public boolean persistLeague();
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team);
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName);
	public LeagueConstant validateLeague(LeagueModel league);
	public ConferenceConstant validateConference(ConferenceModel conference);
	public DivisionConstant validateDivision(DivisionModel division);
	public TeamConstant validateTeam(TeamModel team);
	public FreeAgentConstant validatePlayer(PlayerModel player);

}

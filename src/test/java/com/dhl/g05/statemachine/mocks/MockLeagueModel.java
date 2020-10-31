package com.dhl.g05.statemachine.mocks;

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
import com.dhl.g05.operation.DbPersistanceMock;
import com.dhl.g05.statemachine.ILeagueModelJson;

public class MockLeagueModel implements ILeagueModelJson{
	private LeagueModel league;

	@Override
	public LeagueModel getLeague() {
		return league;
	}

	@Override
	public void setLeague(LeagueModel league) {
		this.league = league;
	}

	@Override
	public LeagueModel createLeague(String league, List<ConferenceModel> conferencedetail,List<FreeAgentModel> agent, List<CoachModel> coach, List<String> managers,GamePlayConfigModel playConfig) {
		return new LeagueModel(league, conferencedetail, agent, coach, managers,playConfig,new DbPersistanceMock());
	}

	@Override
	public boolean persistLeague() {
		return true;
	}

	@Override
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team) {
		return true;
	}

	@Override
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
		return true;
	}

	@Override
	public TeamModel getCurrentTeam() {
		return new TeamModel();
	}

	@Override
	public LeagueConstant validateLeague(LeagueModel league) {
		return LeagueConstant.Success;
	}

	@Override
	public ConferenceConstant validateConference(ConferenceModel conference) {
		return ConferenceConstant.Success;
	}

	@Override
	public DivisionConstant validateDivision(DivisionModel division) {
		return DivisionConstant.Success;
	}

	@Override
	public TeamConstant validateTeam(TeamModel team) {
		return TeamConstant.Success;
	}

	@Override
	public FreeAgentConstant validatePlayer(PlayerModel player) {
		return FreeAgentConstant.Success;
	}
}

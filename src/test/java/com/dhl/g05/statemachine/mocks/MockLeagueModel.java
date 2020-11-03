package com.dhl.g05.statemachine.mocks;

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
import com.dhl.g05.league.LeaguePersistenceMock;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.statemachine.ILeagueModelJson;
import com.dhl.g05.team.TeamConstant;
import com.dhl.g05.team.TeamModel;

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
		return new LeagueModel(league, conferencedetail, agent, coach, managers,playConfig,new LeaguePersistenceMock());
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
	public boolean loadTeam(String teamName) {
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

	@Override
	public Boolean checkTeamNotUnique(String teamName) {
		return false;
	}
}

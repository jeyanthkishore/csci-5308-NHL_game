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
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.statemachine.ILeagueModelJson;
import com.dhl.g05.team.TeamConstant;
import com.dhl.g05.team.TeamModel;

public class MockLeagueModelValidationFails implements ILeagueModelJson{
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
	public LeagueModel createLeague(String league, List<ConferenceModel> conferencedetail,
			List<FreeAgentModel> agent, List<CoachModel> coach, List<String> managers,GamePlayConfigModel gamePlay) {
		return null;
	}

	@Override
	public LeagueConstant validateLeague(LeagueModel league) {
		return LeagueConstant.Failure;
	}

	@Override
	public ConferenceConstant validateConference(ConferenceModel conference) {
		return ConferenceConstant.Failure;
	}

	@Override
	public DivisionConstant validateDivision(DivisionModel division) {
		return DivisionConstant.Failure;
	}

	@Override
	public TeamConstant validateTeam(TeamModel team) {
		return TeamConstant.Failure;
	}

	@Override
	public FreeAgentConstant validatePlayer(PlayerModel player) {
		return FreeAgentConstant.Failure;
	}

	@Override
	public boolean persistLeague() {
		return false;
	}
	
	@Override
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team) {
		return false;
	}

	@Override
	public TeamModel getCurrentTeam() {
		return null;
	}

	@Override
	public boolean loadTeam(String teamName) {
		return false;
	}

	@Override
	public Boolean checkTeamNotUnique(String teamName) {
		return true;
	}

}

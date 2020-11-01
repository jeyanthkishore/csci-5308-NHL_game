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
import com.dhl.g05.statemachine.ILeagueModelJson;

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

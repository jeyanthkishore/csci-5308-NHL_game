package com.dhl.g05.statemachine.mocks;

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
import com.dhl.g05.statemachine.ILeagueModel;

public class MockLeagueModelValidationFails implements ILeagueModel{
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
			List<FreeAgentModel> agent, List<CoachModel> coach) {
		return null;
	}

	@Override
	public ValidateEnumModel validateLeague(LeagueModel league) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateConference(ConferenceModel conference) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateDivision(DivisionModel division) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateTeam(TeamModel team) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validatePlayer(PlayerModel player) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateManager(ManagerModel managerObject) {
		return ValidateEnumModel.Failure;
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
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
		return false;
	}

	@Override
	public TeamModel getCurrentTeam() {
		return null;
	}

}

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
import com.dhl.g05.operation.DbPersistanceMock;
import com.dhl.g05.statemachine.ILeagueModel;

public class MockLeagueModel implements ILeagueModel{
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
	public LeagueModel createLeague(String league, List<ConferenceModel> conferencedetail,List<FreeAgentModel> agent, List<CoachModel> coach) {
		return new LeagueModel(league, conferencedetail, agent, coach,new DbPersistanceMock());
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
	public ValidateEnumModel validateLeague(LeagueModel league) {
		return ValidateEnumModel.Success;
	}

	@Override
	public ValidateEnumModel validateConference(ConferenceModel conference) {
		return ValidateEnumModel.Success;
	}

	@Override
	public ValidateEnumModel validateDivision(DivisionModel division) {
		return ValidateEnumModel.Success;
	}

	@Override
	public ValidateEnumModel validateTeam(TeamModel team) {
		return ValidateEnumModel.Success;
	}

	@Override
	public ValidateEnumModel validatePlayer(PlayerModel player) {
		return ValidateEnumModel.Success;
	}

	@Override
	public ValidateEnumModel validateManager(ManagerModel managerObject) {
		return ValidateEnumModel.Success;
	}

}

package com.dhl.g05.statemachine.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.statemachine.ILeagueModel;

public class MockLeagueModelValidationFails implements ILeagueModel{
private LeagueObject league;
	
	@Override
	public LeagueObject getLeague() {
		return league;
	}
	
	@Override
	public void setLeague(LeagueObject league) {
		this.league = league;
	}

	@Override
	public LeagueObject createLeague(String league, List<ConferenceObject> conferencedetail,
			List<FreeAgentObject> agent, List<CoachObject> coach) {
		return null;
	}

	@Override
	public ValidateEnumModel validateLeague(LeagueObject league) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateConference(ConferenceObject conference) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateDivision(DivisionObject division) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validateTeam(TeamObject team) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public ValidateEnumModel validatePlayer(PlayerObject player) {
		return ValidateEnumModel.Failure;
	}

	@Override
	public String validateManager(ManagerObject managerObject) {
		return "fail";
	}

	@Override
	public boolean persistLeague() {
		return false;
	}
	
	@Override
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamObject team) {
		return false;
	}

	@Override
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
		return false;
	}

	@Override
	public TeamObject getCurrentTeam() {
		return null;
	}

}

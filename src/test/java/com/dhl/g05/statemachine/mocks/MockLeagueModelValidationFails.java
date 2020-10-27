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
	public String validateLeague(LeagueObject league) {
		return "fail";
	}

	@Override
	public String validateConference(ConferenceObject conference) {
		return "fail";
	}

	@Override
	public String validateDivision(DivisionObject division) {
		return "fail";
	}

	@Override
	public String validateTeam(TeamObject team) {
		return "fail";
	}

	@Override
	public String validatePlayer(PlayerObject player) {
		return "fail";
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

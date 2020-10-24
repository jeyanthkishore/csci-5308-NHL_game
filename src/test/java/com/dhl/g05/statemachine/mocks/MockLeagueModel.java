package com.dhl.g05.statemachine.mocks;

import java.util.List;

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.operation.DbPersistanceMock;
import com.dhl.g05.statemachine.ILeagueModel;

public class MockLeagueModel implements ILeagueModel{
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
	public LeagueObject createLeague(String league, List<ConferenceObject> conferencedetail,List<FreeAgentObject> agent, List<CoachObject> coach) {
		return new LeagueObject(league, conferencedetail, agent, coach,new DbPersistanceMock());
	}


	@Override
	public boolean persistLeague() {
		return true;
	}
	
	@Override
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamObject team) {
		return true;
	}

	@Override
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
		return true;
	}

	@Override
	public TeamObject getCurrentTeam() {
		return new TeamObject();
	}

	@Override
	public String validateLeague(LeagueObject league) {
		return "Success";
	}

	@Override
	public String validateConference(ConferenceObject conference) {
		return "Success";
	}

	@Override
	public String validateDivision(DivisionObject division) {
		return "Success";
	}

	@Override
	public String validateTeam(TeamObject team) {
		return "Success";
	}

	@Override
	public String validatePlayer(PlayerObject player) {
		return "Success";
	}

}

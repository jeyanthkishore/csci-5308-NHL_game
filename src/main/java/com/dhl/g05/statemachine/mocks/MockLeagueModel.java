package com.dhl.g05.statemachine.mocks;

import com.dhl.g05.filehandler.ILeagueModelJson;
import com.dhl.g05.league.LeagueModel;
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

}

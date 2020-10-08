package com.dhl.g05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leagueobjects.ConferenceObject;
import com.dhl.g05.leagueobjects.DivisionObject;
import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;
import com.dhl.g05.leagueobjects.TeamObject;

public class LeagueModel implements ILeagueModel{
	public LeagueObject league;
	
	@Override
	public LeagueObject getLeague() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setLeague(LeagueObject league) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LeagueObject createLeague(String league, ArrayList<ConferenceObject> conferencedetail,
			ArrayList<PlayerObject> agent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConferenceObject createConference(String conference, List<DivisionObject> divisiondetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DivisionObject createDivision(String division, ArrayList<TeamObject> teamdetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamObject createTeam(String team, String coach, String manager, ArrayList<PlayerObject> players) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerObject createPlayer(Map<String, Object> players) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateLeague(LeagueObject league) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateConference(ConferenceObject conference) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateDivision(DivisionObject division) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateTeam(TeamObject team) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePlayer(PlayerObject player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean persistLeague() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team) {
		// TODO Auto-generated method stub
		return false;
	}

	

}

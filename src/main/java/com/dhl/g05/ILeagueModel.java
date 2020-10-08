package com.dhl.g05;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dhl.g05.leagueobjects.ConferenceObject;
import com.dhl.g05.leagueobjects.DivisionObject;
import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;
import com.dhl.g05.leagueobjects.TeamObject;

public interface ILeagueModel {
	public LeagueObject getLeague();
	public void setLeague(LeagueObject league);
	public LeagueObject createLeague(String league, ArrayList<ConferenceObject> conferencedetail, ArrayList<PlayerObject> agent);
	public ConferenceObject createConference(String conference, List<DivisionObject> divisiondetail);
	public DivisionObject createDivision(String division, ArrayList<TeamObject> teamdetail);
	public TeamObject createTeam(String team, String coach, String manager, ArrayList<PlayerObject> players);
	public PlayerObject createPlayer(Map<String, Object> players);
	public boolean validateLeague(LeagueObject league);
	public boolean validateConference(ConferenceObject conference);
	public boolean validateDivision(DivisionObject division);
	public boolean validateTeam(TeamObject team);
	public boolean validatePlayer(PlayerObject player);
	public boolean persistLeague();
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team);
	public boolean loadTeam(Map<String, Object> teamDetails);

}

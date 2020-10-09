package com.dhl.g05;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;


public interface ILeagueModel {
	public LeagueObject getLeague();
	public void setLeague(LeagueObject league);
	public LeagueObject createLeague(String league, ArrayList<ConferenceObject> conferencedetail, ArrayList<PlayerObject> agent);
	public ConferenceObject createConference(String conference, List<DivisionObject> divisiondetail);
	public DivisionObject createDivision(String division, ArrayList<TeamObject> teamdetail);
	public TeamObject createTeam(String team, String coach, String manager, ArrayList<PlayerObject> players);
	public PlayerObject createPlayer(String name, String position, Boolean captain);
	public String validateLeague(LeagueObject league);
	public String validateConference(ConferenceObject conference);
	public String validateDivision(DivisionObject division);
	public String validateTeam(TeamObject team);
	public String validatePlayer(PlayerObject player);
	public boolean persistLeague();
	public boolean addTeam(String conferenceName, String divisionName, TeamObject team);
	public boolean loadTeam(Map<String, Object> teamDetails);

}

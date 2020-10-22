package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.leaguemodel.*;


public interface ILeagueModel {
	public LeagueObject getLeague();
	public TeamObject getCurrentTeam();
	public LeagueObject createLeague(String leagueName, List<ConferenceObject> conferences, List<FreeAgentObject> freeAgents, List<CoachObject> coaches);
	public void setLeague(LeagueObject league);
	public boolean persistLeague();
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamObject team);
	public boolean loadTeam(String leagueName, String conferenceName, String divisionName, String teamName);
	public String validateLeague(LeagueObject league);
	public String validateConference(ConferenceObject conference);
	public String validateDivision(DivisionObject division);
	public String validateTeam(TeamObject team);
	public String validatePlayer(PlayerObject player);

}

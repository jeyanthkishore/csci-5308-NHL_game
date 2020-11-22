package com.dhl.g05.filehandler;

import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;


public interface ILeagueModelJson {
	
	public LeagueModel getLeague();
	public ITeam getCurrentTeam();
	public void setLeague(LeagueModel league);
	public boolean persistLeague();
	public boolean addTeamToCurrentLeague(String conferenceName, String divisionName, TeamModel team);
	public boolean loadTeam(String teamName);

}

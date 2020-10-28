package com.dhl.g05.leaguemodel;

import com.dhl.g05.leaguemodel.coach.CoachObject;
import com.dhl.g05.leaguemodel.conference.ConferenceObject;
import com.dhl.g05.leaguemodel.division.DivisionObject;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentObject;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.leaguemodel.player.PlayerObject;
import com.dhl.g05.leaguemodel.team.TeamObject;

public interface ILeagueModelComplete {

	public void loadTeamModelData(TeamObject teamObject);
	
	public void loadPlayerModelData(PlayerObject playerObject);
	
	public void LoadDivisionModelData(DivisionObject divisionObject);
	
	public void loadLeagueModelData(LeagueObject leaguObject);
	
	public void loadConferenceModelData(ConferenceObject conferenceObject);

	public void loadPlayerModelData(FreeAgentObject freeAgentObject);

	public void loadCoachModelData(CoachObject coachObject);
}

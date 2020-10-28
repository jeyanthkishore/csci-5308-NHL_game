package com.dhl.g05.leaguemodel;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public interface ILeagueModelComplete {

	public void loadTeamModelData(TeamModel teamObject);
	
	public void loadPlayerModelData(PlayerModel playerObject);
	
	public void LoadDivisionModelData(DivisionModel divisionObject);
	
	public void loadLeagueModelData(LeagueModel leaguObject);
	
	public void loadConferenceModelData(ConferenceModel conferenceObject);

	public void loadPlayerModelData(FreeAgentModel freeAgentObject);

	public void loadCoachModelData(CoachModel coachObject);
}

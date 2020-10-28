package com.dhl.g05.leaguemodel.team;

import com.dhl.g05.leaguemodel.coach.CoachObject;

public interface ITeamModelPersistence {
	
	public int saveTeamObject(int divisionId, TeamObject teamObject, CoachObject coachObject);
	
	public int loadTeamObject(int divisionId, TeamObject teamObject, CoachObject coachObject);

}

package com.dhl.g05.leaguemodel.team;

import com.dhl.g05.leaguemodel.coach.CoachModel;

public interface ITeamModelPersistence {

	public int saveTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject);

	public int loadTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject);

}

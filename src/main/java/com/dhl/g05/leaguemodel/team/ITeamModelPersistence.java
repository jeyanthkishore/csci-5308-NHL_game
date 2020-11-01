package com.dhl.g05.leaguemodel.team;

import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;

public interface ITeamModelPersistence {

	public int saveTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject);

	public int loadTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject);

	public List<HashMap<String, Object>> loadAllTeamName();

}

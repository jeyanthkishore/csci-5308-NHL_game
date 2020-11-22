package com.dhl.g05.team;

import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.ICoach;

public interface ITeamModelPersistence {

	public int saveTeamObject(int divisionId, ITeam teamObject, ICoach coachObject);

	public int loadTeamObject(int divisionId, ITeam teamObject, ICoach coachObject);

	public List<HashMap<String, Object>> loadAllTeamName();

}

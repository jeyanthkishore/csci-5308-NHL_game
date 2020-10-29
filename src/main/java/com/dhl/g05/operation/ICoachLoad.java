package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;

public interface ICoachLoad {

	public List<CoachModel> loadLeagueCoachObject(String leagueName);
	
}

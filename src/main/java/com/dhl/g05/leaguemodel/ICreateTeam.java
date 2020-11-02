package com.dhl.g05.leaguemodel;

import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.team.TeamModel;

public interface ICreateTeam {

	public boolean teamCreation(String TeamName);
	public List<FreeAgentModel> getFreeAgentList();
	public List<CoachModel> getCoachList();
	public List<String> getManagerList();
	public TeamModel getNewTeam();
}

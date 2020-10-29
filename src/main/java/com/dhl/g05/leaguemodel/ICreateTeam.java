package com.dhl.g05.leaguemodel;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.manager.ManagerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public interface ICreateTeam {

	public boolean teamCreation(String TeamName);
	public List<FreeAgentModel> getFreeAgentList();
	public List<CoachModel> getCoachList();
	public List<ManagerModel> getManagerList();
	public TeamModel getNewTeam();
}

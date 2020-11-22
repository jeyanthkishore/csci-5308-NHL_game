package com.dhl.g05.team;

import java.util.List;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.freeagent.IFreeAgent;

public interface ICreateTeam {

	public boolean teamCreation(String TeamName);
	public List<IFreeAgent> getFreeAgentList();
	public List<ICoach> getCoachList();
	public List<String> getManagerList();
	public TeamModel getNewTeam();
}

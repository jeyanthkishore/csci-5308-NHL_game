package com.dhl.g05.model;

import java.util.List;

import com.dhl.g05.database.ITeamDatabaseOperation;

public interface ITeam {

	Boolean getUserTeam();

	void setUserTeam(Boolean userTeam);

	String getTeamName();

	void setTeamName(String teamName);

	String getGeneralManagerName();

	void setGeneralManagerName(String managerName);

	List<IPlayer> getPlayerList();

	void setPlayerList(List<IPlayer> playerList);

	ICoach getCoachDetails();

	void setCoachDetails(ICoach coachDetails);

	public int getLossCount();

	public int numberOfSkaters(ITeam team);

	public int numberOfGoalies(ITeam team);

	public void assignOneCaptain(ITeam team);

	boolean removeRetiredPlayerFromTeam(IPlayer player);

	public boolean isTeamBalanced(ITeam team);

	public void adjustTeamRoasterAfterDraft(ITeam team);

	public void setLossCount(int lossCount);

	double getTeamStrength();

	void setTeamStrength(double teamStrength);

	TeamConstant validate();

	boolean isTeamExist(String teamName, ITeamDatabaseOperation checkTeam);
	
	double calculateTeamStrength(List<IPlayer> playerList);

}

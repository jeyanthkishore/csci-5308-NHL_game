package com.dhl.g05.team;

import java.util.List;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerModel;

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

	public List<IPlayer> adjustTeamRoasterAfterDraft(ITeam team);

	public void setLossCount(int lossCount);

	double getTeamStrength();

	void setTeamStrength(double teamStrength);

	int saveTeamObject(int divisionId, ITeamModelPersistence database);

	int loadTeamObject(int divisionId, ITeamModelPersistence database);

	boolean checkTeamNotUnique(String teamName, ITeamModelPersistence database);

	TeamConstant validate();

}

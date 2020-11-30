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

	int getLossCount();

	void assignOneCaptain(ITeam team);

	void adjustTeamRoasterAfterDraft(ITeam team);

	void setLossCount(int lossCount);

	List<IPlayer> setActiveRoster(ITeam team);

	List<IPlayer> setInActiveRoster(ITeam team);

	double getTeamStrength();

	void setTeamStrength(double teamStrength);

	TeamConstant validate();

	boolean isTeamExist(String teamName, ITeamDatabaseOperation checkTeam);

	double calculateTeamStrength(List<IPlayer> playerList);

}

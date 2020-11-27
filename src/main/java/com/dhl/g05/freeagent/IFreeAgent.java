package com.dhl.g05.freeagent;

import java.util.List;

import com.dhl.g05.player.IPlayer;

public interface IFreeAgent {

	int getBirthDay();

	void setBirthDay(int day);

	int getBirthMonth();

	void setBirthMonth(int month);

	int getBirthYear();

	String getPlayerName();

	void setPlayerName(String player) ;

	String getPosition();

	void setPosition(String position);

	int getAge();

	void setAge(int age);

	double getSkating();

	void setSkating(double skating);

	double getShooting();

	void setShooting(double shooting);

	double getChecking();

	void setChecking(double checking);

	double getSaving();

	void setSaving(double saving);

	double getPlayerStrength();

	void setPlayerStrength(double playerStrength);

	double calculatePlayerStrength();

	FreeAgentConstant validate();

	boolean isPlayerPositionNotValid();

	boolean getInjuryStatus();

	void setInjuryStatus(boolean isInjured);

	boolean getRetirementStatus();

	void setRetirementStatus(boolean isRetired);

	void setBirthYear(int year);

	List<IFreeAgent> ConvertPlayerToFreeAgent(List<IPlayer> releaseExtraPlayers);

	void calculateAge();

}

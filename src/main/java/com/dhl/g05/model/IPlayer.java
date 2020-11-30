package com.dhl.g05.model;

import java.time.LocalDate;

import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IInjury;

public interface IPlayer {

	String getPlayerName();

	void setPlayerName(String player);

	String getPosition();

	void setPosition(String position);

	Boolean getCaptain();

	void setCaptain(Boolean captain);

	double getSkating();

	void setSkating(double skating);

	double getShooting();

	void setShooting(double shooting);

	double getChecking();

	void setChecking(double checking);

	double getSaving();

	void setSaving(double saving);

	boolean getInjuryStatus();

	void setInjuryStatus(boolean injury);

	int getInjuredForNumberOfDays();

	void setInjuredForNumberOfDays(int injuredForNumberOfDays);

	boolean getRetirementStatus();

	void setRetirementStatus(boolean value);

	double getPlayerStrength();

	void setPlayerStrength(double playerStrength);

	double calculatePlayerStrength();

	int getBirthDay();

	void setBirthDay(int day);

	int getBirthMonth();

	public void setIsActive(boolean active);

	void setBirthMonth(int month);

	int getBirthYear();

	void setBirthYear(int year);

	int getAge();

	void setAge(int age);

	public boolean getIsActive();

	void calculateAge(LocalDate leagueDate);

	void decreasePlayerStatOnBirthday(IPlayer player, IAging agingConfig);

	FreeAgentConstant validate();

	boolean checkPlayerInjury(IPlayerInjured playerInjured, IPlayer player, IInjury injury);
	
	IPlayer convertFreeAgentToPlayer(IFreeAgent freeAgent,boolean captainValue);
	
}

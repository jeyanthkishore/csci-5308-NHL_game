package com.dhl.g05.model;

import java.time.LocalDate;
import java.util.List;
import com.dhl.g05.simulation.statemachine.IAging;

public interface IFreeAgent {

	int getBirthDay();

	void setBirthDay(int day);

	int getBirthMonth();

	void setBirthMonth(int month);

	int getBirthYear();

	String getPlayerName();

	void setPlayerName(String player);

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

	boolean isbirthYearValid();

	boolean isbirthDayValid();

	boolean isBirthDateNotValid();

	boolean isbirthMonthValid();

	FreeAgentConstant validate();

	boolean getInjuryStatus();

	void setInjuryStatus(boolean isInjured);

	boolean getRetirementStatus();

	void setRetirementStatus(boolean isRetired);

	void decreaseFreeAgentStatOnBirthday(IFreeAgent player, IAging agingConfig);

	void setBirthYear(int year);

	List<IFreeAgent> ConvertPlayerToFreeAgent(List<IPlayer> releaseExtraPlayers);

	void calculateAge(LocalDate leagueDate);

}

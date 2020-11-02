package com.dhl.g05.freeagent;

public interface IFreeAgent {

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

	boolean getInjuredStatus();

	void setInjuredStatus(boolean isInjured);

	boolean getRetiredStatus();

	void setRetiredStatus(boolean isRetired);
}

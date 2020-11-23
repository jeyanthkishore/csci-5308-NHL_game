package com.dhl.g05.player;

import com.dhl.g05.freeagent.FreeAgentConstant;

public interface IPlayer {

    Boolean getCaptain();

    void setCaptain(Boolean captain);

    int getInjuredForNumberOfDays();

    void setInjuredForNumberOfDays(int injuredForNumberOfDays);
    
    String getPosition();

	void setPosition(String position);

	FreeAgentConstant validate();

	int savePlayerObject(int teamId, IPlayerModelPersistence playerDatabase);

	int loadPlayerObject(int teamId, IPlayerModelPersistence playerDatabase);

	void incrementPlayerAgeByDay(int days);

	boolean getInjuredStatus();

	void setInjuredStatus(boolean injury);
	
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
	
	int getBirthDay();

	int getBirthMonth();

	int getBirthYear();

	void setRetiredStatus(boolean value);

	int getAge();

	void setAge(int age);

}

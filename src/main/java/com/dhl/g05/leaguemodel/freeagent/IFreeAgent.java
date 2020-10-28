package com.dhl.g05.leaguemodel.freeagent;

public interface IFreeAgent {

	public String getPlayerName();

	public void setPlayerName(String player) ;

	public String getPosition();

	public void setPosition(String postition);

	public double getAge();

	public void setAge(double age);

	public double getSkating();

	public void setSkating(double skating);

	public double getShooting();

	public void setShooting(double shooting);

	public double getChecking();

	public void setChecking(double checking);

	public double getSaving();

	public void setSaving(double saving);

	public double getPlayerStrength();

	public void setPlayerStrength(double playerStrength);

	public double calculatePlayerStrength();

	public FreeAgentConstant validate();

	public boolean isPlayerDetailsNull();

	public boolean isPlayerDetailsEmpty();

	public boolean isPlayerPositionValid() ;

	public boolean isPlayerAgeValid();

	public boolean isPlayerStatValid();

	public boolean validateStat(double stat);

	public boolean getHasInjured();

	public void setHasInjured(boolean hasInjured);
}

package com.dhl.g05.leaguemodel;

public class FreeAgentObject {
	
	private String playerName;

	private String position;

	private String result;

	// Added Player Stat

	private double age;

	private double skating;

	private double shooting;

	private double checking;

	private double saving;

	public FreeAgentObject() {

		setPlayerName(null);

		setPosition(null);

	}

	public FreeAgentObject(String playerName, String position, double age, double skating, double shooting, double checking, double saving) {

		this.playerName = playerName;

		this.position = position;

		this.age = age;

		this.skating = skating;

		this.shooting = shooting;

		this.checking = checking;

		this.saving = saving;

		result = validate();

	}

	public FreeAgentObject(ILeagueModel player) {

		player.loadPlayerModelData(this);

	}

	public String getResult() {

		return result;

	}

	public void setResult(String result) {

		this.result = result;

	}

	public String getPlayerName() {

		return playerName;

	}

	public void setPlayerName(String player) {

		playerName = player;

	}

	public String getPosition() {

		return position;

	}

	public void setPosition(String postition) {

		this.position = postition;

	}

	public double getAge() {

		return age;

	}

	public void setAge(double age) {

		this.age = age;

	}

	public double getSkating() {

		return skating;

	}

	public void setSkating(double skating) {

		this.skating = skating;

	}

	public double getShooting() {

		return shooting;

	}

	public void setShooting(double shooting) {

		this.shooting = shooting;

	}

	public double getChecking() {

		return checking;

	}

	public void setChecking(double checking) {

		this.checking = checking;

	}

	public double getSaving() {

		return saving;

	}

	public void setSaving(double saving) {

		this.saving = saving;

	}


	public String validate() {

		if(isPlayerDetailsNull()||isPlayerDetailsEmpty()) {

			return "Player Should Not have Empty Value";

		}

		if(!isPlayerPositionValid()) {

			return "Player Position Is Wrong";

		}

		if(!isPlayerAgeValid()) {

			return "Player age is invalid";

		}

		if(!isPlayerStatValid()) {

			return "Invalid state of player";

		}

		return "success";
	
	}

	public boolean isPlayerDetailsNull() {

		if(playerName == null || position ==null) {

			return true;

		}

		return false;

	}

	public boolean isPlayerDetailsEmpty() {

		if(playerName == "" || position =="") {

				return true;
		}

		return false;

	}
		
	public boolean isPlayerPositionValid() {

			if(position.equals("forward") || position.equals("defense") || position.equals("goalie")) {

				return true;

			}

			return false;

	}

	public boolean isPlayerAgeValid() {

		if (age > 0) {

			return true;

		}

		return  false;

	}

	public boolean isPlayerStatValid() {

		if (validateStat(skating) && validateStat(shooting) && validateStat(checking) && validateStat(saving)) {

			return true;

		}

		return false;

	}

	public boolean validateStat(double stat) {

		if (stat >= 0 && stat <= 20) {

			return true;
		}

		return false;

	}
}

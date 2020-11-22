package com.dhl.g05.team;

import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.coach.ICoach;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerModel;
import com.mysql.cj.util.StringUtils;

public class TeamModel implements ITeam {

	private String teamName;
	private ICoach headCoach;
	private String generalManager;
	private List<IPlayer> players;
	private double teamStrength;
	private Boolean userTeam;
	private int LossCount;

	public TeamModel() {
		setGeneralManagerName(null);
		setTeamName(null);
		setPlayerList(null);
		setCoachDetails(null);
		setUserTeam(false);
	}

	public TeamModel(ITeamModel teamObject) {
		teamObject.loadTeamModelData(this);
	}

	public TeamModel(String team, CoachModel coachDetails, String manager, List<IPlayer> players) {
		this.teamName = team;
		this.headCoach = coachDetails;
		this.generalManager = manager;
		this.players = players;
		this.userTeam = false;
	}

	public Boolean getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(Boolean userTeam) {
		this.userTeam = userTeam;
	}

	public int getLossCount() {
		return LossCount;
	}

	public void setLossCount(int lossCount) {
		LossCount = lossCount;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getGeneralManagerName() {
		return generalManager;
	}

	public void setGeneralManagerName(String managerName) {
		this.generalManager = managerName;
	}

	public List<IPlayer> getPlayerList() {
		return players;
	}

	public void setPlayerList(List<IPlayer> playerList) {
		this.players = playerList;
	}

	public ICoach getCoachDetails() {
		return headCoach;
	}

	public void setCoachDetails(ICoach coach) {
		this.headCoach = coach;
	}

	public double getTeamStrength() {
		return teamStrength;
	}

	public void setTeamStrength(double teamStrength) {
		this.teamStrength = teamStrength;
	}

	public int saveTeamObject(int divisionId, ITeamModelPersistence database) {
		return database.saveTeamObject(divisionId, this, headCoach);
	}

	public int loadTeamObject(int divisionId, ITeamModelPersistence database) {
		return database.loadTeamObject(divisionId, this, headCoach);
	}

	public double calculateTeamStrength(List<IPlayer> playerList) {
		for (IPlayer player : playerList) {
			if (player.getInjuredStatus()) {
				teamStrength += player.calculatePlayerStrength() / 2;
			} else {
				teamStrength += player.calculatePlayerStrength();
			}
		}
		return teamStrength;
	}

	public TeamConstant validate() {
		if (isTeamDetailsEmptyOrNull()) {
			return TeamConstant.TeamDetailsEmpty;
		}
		if (isPlayerListEmpty()) {
			return TeamConstant.PlayerListEmpty;
		}
		if (isPlayerListValid()) {
			return TeamConstant.PlayerCountMismatch;
		}
		if (containOneTeamCaptain() == 0) {
			return TeamConstant.NoTeamCaptain;
		}
		if (containOneTeamCaptain() > 1) {
			return TeamConstant.MoreTeamCaptain;
		}
		if (isCoachDetailsEmptyOrNull()) {
			return TeamConstant.CoachDetailsEmpty;
		}
		return TeamConstant.Success;
	}

	private boolean isTeamDetailsEmptyOrNull() {
		if (StringUtils.isNullOrEmpty(generalManager) || StringUtils.isNullOrEmpty(teamName)) {
			return true;
		}
		return false;
	}

	private boolean isPlayerListEmpty() {
		return (players == null || players.isEmpty());
	}

	private boolean isPlayerListValid() {
		if (players.size() > 20) {
			return true;
		}
		return false;
	}

	public long containOneTeamCaptain() {
		long captainCount = players.stream().filter(p -> p.getCaptain().equals(true)).count();
		return captainCount;
	}

	private boolean isCoachDetailsEmptyOrNull() {
		if (headCoach == null) {
			return true;
		}
		return false;
	}

	public int numberOfSkaters(ITeam team)
	{
		int skater = 0;
		for (IPlayer player : team.getPlayerList()) {
			if ((player.getPosition().equalsIgnoreCase("Forward"))
					|| (player.getPosition().equalsIgnoreCase("Defense"))) {
				skater++;
			}
	}
		return skater;
	}
	
	public int numberOfGoalies(ITeam team)
	{
		int goalie = 0;
		for (IPlayer player : team.getPlayerList()) {
			if (player.getPosition().equalsIgnoreCase("Goalie")) {
				goalie++;
			}
	}
		return goalie;
	}
	public boolean isTeamBalanced(ITeam team) {
		int goalie=numberOfSkaters(team);
		int skater=numberOfGoalies(team);
		if (goalie == 2 && skater == 18) {
			return true;
		} else {
			return false;
		}
	}
	
	public void assignOneCaptain(ITeam team) {
		int captainCount = 0;
		for (int i = 1; i < team.getPlayerList().size(); i++) {
			if (team.getPlayerList().get(i).getCaptain() == true) {
				captainCount++;
				if (captainCount > 1) {
					team.getPlayerList().get(i).setCaptain(false);
				}
			}
		}
	}
	
	public boolean checkTeamNotUnique(String teamName,ITeamModelPersistence database) {
		List<HashMap<String,Object>> teamNameList = database.loadAllTeamName();
		for(HashMap<String,Object> team : teamNameList) {
			if(team.get("team_name").equals(teamName)){
				return true;
			}
		}
		return false;
	}
}

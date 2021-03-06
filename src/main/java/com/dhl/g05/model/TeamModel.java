package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.database.ITeamDatabaseOperation;
import com.google.gson.annotations.Expose;
import com.mysql.cj.util.StringUtils;

public class TeamModel implements ITeam {

	static final Logger logger = LogManager.getLogger(TeamModel.class);
	private static final int numberOfDefense = 10;
	private static final int numberOfForward = 16;
	private static final int numberOfGoalie = 4;
	private static final int numberOfActiveGoalies=2;
	private static final int numberOfActiveSKaters=18;
	private static final int numberOfActivePlayers = 20;
	private static final int numberOfInActivePlayers = 10;
	private static final int numberOfPlayers = 30;

	@Expose
	private String teamName;
	@Expose
	private ICoach headCoach;
	@Expose
	private String generalManager;
	@Expose
	private List<IPlayer> players;
	@Expose
	private double teamStrength;
	@Expose
	private Boolean userTeam;
	@Expose
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

	@Override
	public ICoach getCoachDetails() {
		return headCoach;
	}

	@Override
	public void setCoachDetails(ICoach coach) {
		this.headCoach = coach;
	}

	@Override
	public double getTeamStrength() {
		return teamStrength;
	}

	@Override
	public void setTeamStrength(double teamStrength) {
		this.teamStrength = teamStrength;
	}

	@Override
	public boolean isTeamExist(String teamName, ITeamDatabaseOperation checkTeam) {
		return checkTeam.isTeamExist(teamName);
	}

	@Override
	public double calculateTeamStrength(List<IPlayer> playerList) {
		logger.info("Calculating team strength using players strength");
		for (IPlayer player : playerList) {
			if (player.getInjuryStatus()) {
				teamStrength += player.calculatePlayerStrength() / 2;
			} else {
				teamStrength += player.calculatePlayerStrength();
			}
		}
		return teamStrength;
	}

	@Override
	public boolean removeRetiredPlayerFromTeam(IPlayer player) {
		logger.info("Removing retired player from team");
		int numberOfPlayer = players.size();
		if (numberOfPlayer > 0) {
			players.remove(player);
			return true;
		}
		return false;
	}

	@Override
	public TeamConstant validate() {
		logger.info("Validating team details of Team : "+getTeamName());
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
		logger.info("team validated successfully");
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
		if (players.size() > numberOfPlayers)   {
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
	
	public List<IPlayer> setActiveRoster(ITeam team) {
		int skaterCount = 0;
		int goalieCount = 0;
		int activeRosterSize = 0;
		List<IPlayer> players = team.getPlayerList();
		List<IPlayer> activePlayers = new ArrayList<>();
		players.sort(Comparator.comparing(IPlayer::getPlayerStrength).reversed());
		for (IPlayer p : players) {
			if (activeRosterSize == numberOfActivePlayers) {
				break;
			} else {
				if ((p.getPosition().equals(PositionConstant.defense.getValue()) || (p.getPosition().equals(PositionConstant.forward.getValue())))) {
					if (skaterCount == numberOfActiveSKaters) {
						continue;
					} else {
						p.setIsActive(BooleanValue.True.getValue());
						skaterCount++;
						activeRosterSize++;
						activePlayers.add(p);
					}
				}
				if (p.getPosition().equals(PositionConstant.goalie.getValue())) {
					if (goalieCount == numberOfActiveGoalies) {
						continue;
					} else {
						p.setIsActive(BooleanValue.True.getValue());
						goalieCount++;
						activeRosterSize++;
						activePlayers.add(p);
					}
				}
			}
		}
		team.setPlayerList(activePlayers);
		return activePlayers;
	}
				
	public List<IPlayer> setInActiveRoster(ITeam team) {
		int activeRosterSize = 0;
		List<IPlayer> InActivePlayers = new ArrayList<>();
		List<IPlayer> players = team.getPlayerList();
		players.sort(Comparator.comparing(IPlayer::getPlayerStrength));
		for (IPlayer p : players) {
			if (activeRosterSize == numberOfInActivePlayers) {
				break;
			} else {
				p.setIsActive(BooleanValue.False.getValue());
				InActivePlayers.add(p);
				activeRosterSize++;
			}
		}
		team.setPlayerList(InActivePlayers);
		return InActivePlayers;
	}

	public void adjustTeamRoasterAfterDraft(ITeam team) {
		List<IPlayer> allPlayers = team.getPlayerList();
		allPlayers.sort(Comparator.comparing(IPlayer::getPlayerStrength).reversed());
		List<IPlayer> adjustedTeam = new ArrayList<>();
		List<IPlayer> releaseExtraPlayers = new ArrayList<>();
		int defenseCount = 0;
		int forwardCount = 0;
		int goalieCount = 0;
		for (IPlayer p : allPlayers) {
			if (p.getPosition().equals(PositionConstant.defense.getValue())) {
				if (defenseCount == numberOfDefense) {
					releaseExtraPlayers.add(p);
					continue;
				} else {
					adjustedTeam.add(p);
					defenseCount++;
				}
			}
			if (p.getPosition().equals(PositionConstant.forward.getValue())) {
				if (forwardCount == numberOfForward) {
					releaseExtraPlayers.add(p);
					continue;
				} else {
					adjustedTeam.add(p);
					forwardCount++;
				}
			}
			if (p.getPosition().equals(PositionConstant.goalie.getValue())) {
				if (goalieCount == numberOfGoalie) {
					releaseExtraPlayers.add(p);
					continue;
				} else {
					adjustedTeam.add(p);
					goalieCount++;
				}
			}
		}
		IFreeAgent agent= ApplicationConfiguration.instance().getModelConcreteFactoryState().createFreeAgentModel();
		agent.ConvertPlayerToFreeAgent(releaseExtraPlayers);
		team.setPlayerList(adjustedTeam);
	}
}

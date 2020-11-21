package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dhl.g05.communication.ITradeCommunication;
import com.dhl.g05.communication.PlayerCommunication;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class ResolveTrade implements IResolveTrade {
	private static final int SKATERS_COUNT = 18;
	private static final int GOALIES_COUNT = 2;
	private static final String FORWARD = "Forward";
	private static final String GOALIE = "Goalie";
	private static final String DEFENSE = "Defense";
	private static final String SKATER = "skater";

	public void resolveTrade() {

		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		IStrongTeam teamAcceptingTrade = TradingConfig.instance().getStrongteam();
		if(teamAcceptingTrade.getStrongTeam().getUserTeam() == null)
		{
			adjustAITeam(teamAcceptingTrade.getStrongTeam());
		}
		if (teamAcceptingTrade.getStrongTeam().getUserTeam() == true) {
			adjustUserTeam(teamInitiatingTrade.getWeakTeam());
		} else {
			adjustAITeam(teamAcceptingTrade.getStrongTeam());
		}
		if (teamInitiatingTrade.getWeakTeam().getUserTeam() == false) {
			adjustAITeam(teamInitiatingTrade.getWeakTeam());
		}

	}

	public void adjustUserTeam(TeamModel team) {
		TeamModel userTeam = new TeamModel();
		int skatersCount = userTeam.numberOfSkaters(team);
		int goaliesCount = userTeam.numberOfGoalies(team);

		if (skatersCount > SKATERS_COUNT) {
			String position = SKATER;
			dropPlayersFromUserTeam(team, position, skatersCount - SKATERS_COUNT);
		} else if (skatersCount < SKATERS_COUNT) {
			String position = SKATER;
			adjustPlayersForUserTeam(team, position, SKATERS_COUNT - skatersCount);
		}
		if (goaliesCount > GOALIES_COUNT) {
			String position = SKATER;
			dropPlayersFromUserTeam(team, position, goaliesCount - GOALIES_COUNT);
		} else if (goaliesCount < GOALIES_COUNT) {
			String position = SKATER;
			adjustPlayersForUserTeam(team, position, GOALIES_COUNT - goaliesCount);
		}
	}

	public void adjustAITeam(TeamModel team) {
		int skatersCount = team.numberOfSkaters(team);
		int goaliesCount = team.numberOfGoalies(team);

		if (skatersCount > SKATERS_COUNT) {
			dropToFreeAgentList(team, SKATER, skatersCount - SKATERS_COUNT);
		} else if (skatersCount < SKATERS_COUNT) {
			adjustPlayersForAI(team, SKATER, SKATERS_COUNT - skatersCount);
		}

		if (goaliesCount > GOALIES_COUNT) {
			dropToFreeAgentList(team, GOALIE, goaliesCount - GOALIES_COUNT);
		} else if (goaliesCount < GOALIES_COUNT) {
			adjustPlayersForAI(team, GOALIE, GOALIES_COUNT - goaliesCount);
		}
	}

	public void dropToFreeAgentList(TeamModel team, String position, int count) {
		LeagueModel leagueDetails = new LeagueModel();
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();
		List<PlayerModel> players = getPlayerPosition(team.getPlayerList(), position);
		List<PlayerModel> weakestPlayers = sortPlayer.sortByDescending(players);
		List<PlayerModel> weakestPLayersToTrade = weakestPlayers.stream().limit(count).collect(Collectors.toList());

		for (PlayerModel player : weakestPLayersToTrade) {
			FreeAgentModel FreeAgentToPlayer = new FreeAgentModel();
			List<FreeAgentModel> dropPlayer = new ArrayList<FreeAgentModel>();
			FreeAgentToPlayer.setPlayerName(player.getPlayerName());
			FreeAgentToPlayer.setPlayerStrength(player.getPlayerStrength());
			FreeAgentToPlayer.setPosition(player.getPosition());
			dropPlayer.add(FreeAgentToPlayer);
			leagueDetails.setFreeAgent(dropPlayer);
		}
	}

	public void adjustPlayersForAI(TeamModel team, String position, int count) {

		List<PlayerModel> playersAdded = team.getPlayerList();
		LeagueModel leagueDetails = new LeagueModel();
		List<FreeAgentModel> freeAgents = getFreeAgentPlayerPosition(leagueDetails.getFreeAgent(), position);
		for (FreeAgentModel freeAgentToSwap : freeAgents) {
			PlayerModel FreeAgentToPlayer = new PlayerModel();
			FreeAgentToPlayer.setPlayerName(freeAgentToSwap.getPlayerName());
			FreeAgentToPlayer.setPlayerStrength(freeAgentToSwap.getPlayerStrength());
			FreeAgentToPlayer.setPosition(freeAgentToSwap.getPosition());
			playersAdded.add(FreeAgentToPlayer);
			team.setPlayerList(playersAdded);
			
		}
	}

	public void dropPlayersFromUserTeam(TeamModel team, String position, int count) {
		ITradeCommunication message = new PlayerCommunication();
		List<PlayerModel> players = getPlayerPosition(team.getPlayerList(), position);
		message.DropPlayerDetails(players, count);
		List<PlayerModel> playerToDrop = new ArrayList<PlayerModel>();

		for (int i = 0; i < count; i++) {
			while (true) {
				String name = message.getResponse();
				if (name.equals(players.get(i).getPlayerName())) {
					playerToDrop.add(players.get(i));
					break;
				} else {
					message.sendMessage("Player not found");
				}
			}
		}
		for (PlayerModel player : playerToDrop) {
			if (playerToDrop.contains(player))
				;
			//team.removeTeamPlayer(player);
		}
	}

	public List<FreeAgentModel> getFreeAgentPlayerPosition(List<FreeAgentModel> players, String position) {

		List<FreeAgentModel> playersWithPosition = new ArrayList<>();
		if (position.equalsIgnoreCase("skater")) {
			for (FreeAgentModel player : players) {
				if (player.getPosition().equalsIgnoreCase("Forward")
						|| player.getPosition().equalsIgnoreCase("Defense")) {
					if (player.getRetiredStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		} else {
			for (FreeAgentModel player : players) {
				if (player.getPosition().equals(position)) {
					if (player.getRetiredStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		}
	}

	public void adjustPlayersForUserTeam(TeamModel team, String freeAgentPosition, int count) {
		LeagueModel leagueDetails = new LeagueModel();
		List<FreeAgentModel> freeAgents = freeAgentsWithPosition(leagueDetails.getFreeAgent(), freeAgentPosition);
		ITradeCommunication message = new PlayerCommunication();
		message.AddPlayerDetails(freeAgents, count);
		List<FreeAgentModel> freeAgentsName = new ArrayList<FreeAgentModel>();
		List<PlayerModel> playersAdded = team.getPlayerList();

		for (int i = 0; i < count; i++) {
			while (true) {
				String name = message.getResponse();
				if (name.equals(freeAgents.get(i).getPlayerName())) {
					freeAgentsName.add(freeAgents.get(i));
					break;
				} else {
					message.sendMessage("Invalid input");
				}
			}
		}

		for (FreeAgentModel player : freeAgentsName) {
			PlayerModel FreeAgentToPlayer = new PlayerModel();
			FreeAgentToPlayer.setPlayerName(player.getPlayerName());
			FreeAgentToPlayer.setPlayerStrength(player.getPlayerStrength());
			FreeAgentToPlayer.setPosition(player.getPosition());
			playersAdded.add(FreeAgentToPlayer);
			team.setPlayerList(playersAdded);
		}

		for (FreeAgentModel player : freeAgentsName) {
			freeAgents.remove(player);
		}
	}

	public List<PlayerModel> getPlayerPosition(List<PlayerModel> players, String position) {

		List<PlayerModel> playersWithPosition = new ArrayList<>();
		if (position.equalsIgnoreCase("skater")) {
			for (PlayerModel player : players) {
				if (player.getPosition().equalsIgnoreCase("Forward")
						|| player.getPosition().equalsIgnoreCase("Defense")) {
					if (player.getRetiredStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		} else {
			for (PlayerModel player : players) {
				if (player.getPosition().equals(position)) {
					if (player.getRetiredStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;

		}
	}

	public List<FreeAgentModel> freeAgentsWithPosition(List<FreeAgentModel> freeAgents, String position) {

		List<FreeAgentModel> freeAgentsWithPosition = new ArrayList<FreeAgentModel>();
		if (position.equalsIgnoreCase(SKATER)) {
			for (FreeAgentModel freeAgent : freeAgents) {
				if (freeAgent.getPosition().equalsIgnoreCase(FORWARD)
						|| freeAgent.getPosition().equalsIgnoreCase(DEFENSE)) {
					if (freeAgent.getRetiredStatus() == true) {
						continue;
					}
					freeAgentsWithPosition.add(freeAgent);
				}
			}
			return freeAgentsWithPosition;
		}

		for (FreeAgentModel freeAgent : freeAgents) {
			if (freeAgent.getPosition().equals(position)) {
				if (freeAgent.getRetiredStatus() == true) {
					continue;
				}
				freeAgentsWithPosition.add(freeAgent);
			}
		}
		return freeAgentsWithPosition;
	}
}

package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dhl.g05.communication.ITradeCommunication;
import com.dhl.g05.communication.PlayerCommunication;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueModel;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.PositionConstant;
import com.dhl.g05.model.TeamModel;

public class ResolveTrade implements IResolveTrade {
	private static final int SKATERS_COUNT = 18;
	private static final int GOALIES_COUNT = 2;
	private static final String SKATER = "skater";

	public void resolveTrade() {

		IWeakTeam teamInitiatingTrade = AbstractTradingFactory.getFactory().getWeakteam();
		IStrongTeam teamAcceptingTrade = AbstractTradingFactory.getFactory().getStrongteam();
		if (teamAcceptingTrade.getStrongTeam().getUserTeam() == null) {
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

	public void adjustUserTeam(ITeam team) {
		ITeam userTeam = new TeamModel();
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

	public void adjustAITeam(ITeam team) {
		int skatersCount = team.numberOfSkaters(team);
		int goaliesCount = team.numberOfGoalies(team);
		if (skatersCount > SKATERS_COUNT) {
			dropToFreeAgentList(team, SKATER, skatersCount - SKATERS_COUNT);
		} else if (skatersCount < SKATERS_COUNT) {
			adjustPlayersForAI(team, SKATER, SKATERS_COUNT - skatersCount);
		}

		if (goaliesCount > GOALIES_COUNT) {
			dropToFreeAgentList(team, PositionConstant.goalie.getValue(), goaliesCount - GOALIES_COUNT);
		} else if (goaliesCount < GOALIES_COUNT) {
			adjustPlayersForAI(team, PositionConstant.goalie.getValue(), GOALIES_COUNT - goaliesCount);
		}
	}

	public void dropToFreeAgentList(ITeam team, String position, int count) {
		LeagueModel leagueDetails = new LeagueModel();
		ISortPlayerStrength sortPlayer = AbstractTradingFactory.instance().getSortplayerstrength();
		List<IPlayer> players = getPlayerPosition(team.getPlayerList(), position);
		List<IPlayer> weakestPlayers = sortPlayer.sortByDescending(players);
		List<IPlayer> weakestPLayersToTrade = weakestPlayers.stream().limit(count).collect(Collectors.toList());

		for (IPlayer player : weakestPLayersToTrade) {
			FreeAgentModel FreeAgentToPlayer = new FreeAgentModel();
			List<IFreeAgent> dropPlayer = new ArrayList<>();
			FreeAgentToPlayer.setPlayerName(((FreeAgentModel) player).getPlayerName());
			FreeAgentToPlayer.setPlayerStrength(player.getPlayerStrength());
			FreeAgentToPlayer.setPosition(player.getPosition());
			dropPlayer.add(FreeAgentToPlayer);
			leagueDetails.setFreeAgent(dropPlayer);
		}
	}

	public void adjustPlayersForAI(ITeam team, String position, int count) {

		List<IPlayer> playersAdded = team.getPlayerList();
		LeagueModel leagueDetails = new LeagueModel();
		List<IFreeAgent> freeAgents = getFreeAgentPlayerPosition(leagueDetails.getFreeAgent(), position);
		for (IFreeAgent freeAgentToSwap : freeAgents) {
			PlayerModel FreeAgentToPlayer = new PlayerModel();
			FreeAgentToPlayer.setPlayerName(freeAgentToSwap.getPlayerName());
			FreeAgentToPlayer.setPlayerStrength(freeAgentToSwap.getPlayerStrength());
			FreeAgentToPlayer.setPosition(freeAgentToSwap.getPosition());
			playersAdded.add(FreeAgentToPlayer);
			team.setPlayerList(playersAdded);
		}
	}

	public void dropPlayersFromUserTeam(ITeam team, String position, int count) {
		ITradeCommunication message = new PlayerCommunication();
		List<IPlayer> players = getPlayerPosition(team.getPlayerList(), position);
		message.DropPlayerDetails(players, count);
		List<IPlayer> playerToDrop = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			while (true) {
				String name = message.getResponse();
				if (name.equals(((FreeAgentModel) players.get(i)).getPlayerName())) {
					playerToDrop.add(players.get(i));
					break;
				} else {
					message.sendMessage("Player not found");
				}
			}
		}
		for (IPlayer player : playerToDrop) {
			if (playerToDrop.contains(player))
				;
			// team.removeTeamPlayer(player);
		}
	}

	public List<IFreeAgent> getFreeAgentPlayerPosition(List<IFreeAgent> players, String position) {

		List<IFreeAgent> playersWithPosition = new ArrayList<>();
		if (position.equalsIgnoreCase(SKATER)) {
			for (IFreeAgent player : players) {
				if (player.getPosition().equals(PositionConstant.forward.getValue())
						|| player.getPosition().equals(PositionConstant.defense.getValue())) {
					if (player.getRetirementStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		} else {
			for (IFreeAgent player : players) {
				if (player.getPosition().equals(position)) {
					if (player.getRetirementStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		}
	}

	public void adjustPlayersForUserTeam(ITeam team, String freeAgentPosition, int count) {
		LeagueModel leagueDetails = new LeagueModel();
		List<IFreeAgent> freeAgents = freeAgentsWithPosition(leagueDetails.getFreeAgent(), freeAgentPosition);
		ITradeCommunication message = new PlayerCommunication();
		message.AddPlayerDetails(freeAgents, count);
		List<IFreeAgent> freeAgentsName = new ArrayList<>();
		List<IPlayer> playersAdded = team.getPlayerList();

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

		for (IFreeAgent player : freeAgentsName) {
			PlayerModel FreeAgentToPlayer = new PlayerModel();
			FreeAgentToPlayer.setPlayerName(player.getPlayerName());
			FreeAgentToPlayer.setPlayerStrength(player.getPlayerStrength());
			FreeAgentToPlayer.setPosition(player.getPosition());
			playersAdded.add(FreeAgentToPlayer);
			team.setPlayerList(playersAdded);
		}

		for (IFreeAgent player : freeAgentsName) {
			freeAgents.remove(player);
		}
	}

	public List<IPlayer> getPlayerPosition(List<IPlayer> list, String position) {

		List<IPlayer> playersWithPosition = new ArrayList<>();
		if (position.equalsIgnoreCase(SKATER)) {
			for (IPlayer player : list) {
				if (player.getPosition().equals(PositionConstant.forward.getValue())
						|| player.getPosition().equals(PositionConstant.defense.getValue())) {
					if (((FreeAgentModel) player).getRetirementStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		} else {
			for (IPlayer player : list) {
				if (player.getPosition().equals(position)) {
					if (((FreeAgentModel) player).getRetirementStatus() == true) {
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		}
	}

	public List<IFreeAgent> freeAgentsWithPosition(List<IFreeAgent> list, String position) {

		List<IFreeAgent> freeAgentsWithPosition = new ArrayList<>();
		if (position.equalsIgnoreCase(SKATER)) {
			for (IFreeAgent freeAgent : list) {
				if (freeAgent.getPosition().equals(PositionConstant.forward.getValue())
						|| freeAgent.getPosition().equals(PositionConstant.defense.getValue())) {
					if (freeAgent.getRetirementStatus() == true) {
						continue;
					}
					freeAgentsWithPosition.add(freeAgent);
				}
			}
			return freeAgentsWithPosition;
		}
		for (IFreeAgent freeAgent : list) {
			if (freeAgent.getPosition().equals(position)) {
				if (freeAgent.getRetirementStatus() == true) {
					continue;
				}
				freeAgentsWithPosition.add(freeAgent);
			}
		}
		return freeAgentsWithPosition;
	}
}

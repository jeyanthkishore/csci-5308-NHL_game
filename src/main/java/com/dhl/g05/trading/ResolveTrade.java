package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.communication.ITradeCommunication;
import com.dhl.g05.communication.PlayerCommunication;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;



public class ResolveTrade implements IResolveTrade {
	int SKATERS_COUNT = 18;
	int GOALIES_COUNT = 2;

	public void resolveTrade() {

		List<PlayerModel> playerList = new ArrayList<PlayerModel>();

		List<TeamModel> teams = new ArrayList<TeamModel>();
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		IStrongTeam teamAcceptingTrade = TradingConfig.instance().getStrongteam();
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();


	}

	public void adjustUserTeamRoaster(TeamModel userTeam) {
		TeamModel team = new TeamModel();
		int skatersCount = team.numberOfSkaters(userTeam);
		int goaliesCount = team.numberOfGoalies(userTeam);

		if (skatersCount > SKATERS_COUNT) {
			String position = "Skater";
			dropPlayersFromUserTeam(userTeam, position, skatersCount - SKATERS_COUNT);
		} else if (skatersCount < SKATERS_COUNT) {
			String position = "Skater";
			adjustPlayersForUserTeam(userTeam, position, SKATERS_COUNT - skatersCount);
		}
		if (goaliesCount > GOALIES_COUNT) {
			String position = "Goalie";
			dropPlayersFromUserTeam(userTeam, position, goaliesCount - GOALIES_COUNT);
		} else if (goaliesCount < GOALIES_COUNT) {
			String position = "Goalie";
			adjustPlayersForUserTeam(userTeam, position, GOALIES_COUNT - goaliesCount);
		}
	}

	public void dropPlayersFromUserTeam(TeamModel team, String position, int count) {
		ITradeCommunication message = new PlayerCommunication();
		List<PlayerModel> players = getPlayerPosition(team.getPlayerList(), position);
		message.DropPlayerDetails(players, count);
		List<String> playerName = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			while (true) {
				String name = message.getResponse();
				if (name.equals(players.get(i).getPlayerName())) {
					playerName.add(players.get(i).getPlayerName());
					break;
				} else {
					message.sendMessage("Player not found");
				}
			}
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
	public void adjustPlayersForUserTeam(TeamModel team, String freeAgentPosition, int count) {
		LeagueModel league= new LeagueModel();
		List<FreeAgentModel> freeAgents = freeAgentsWithPosition(league.getFreeAgent(), freeAgentPosition);
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
					message.sendMessage("Free Agent not found");
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

	public List<FreeAgentModel> freeAgentsWithPosition(List<FreeAgentModel> freeAgents, String position) {

		List<FreeAgentModel> freeAgentsWithPosition = new ArrayList<FreeAgentModel>();
		if (position.equalsIgnoreCase("skater")) {
			for (FreeAgentModel freeAgent : freeAgents) {
				if (freeAgent.getPosition().equalsIgnoreCase("Forward")
						|| freeAgent.getPosition().equalsIgnoreCase("Defense")) {
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

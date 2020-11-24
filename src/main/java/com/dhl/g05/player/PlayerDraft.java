package com.dhl.g05.player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.IStanding;
import com.dhl.g05.team.ITeam;

public class PlayerDraft {

	private static final int draftPickTeamSubstraction = 16;
	private ILeagueStanding leaguestanding;

	public void playerDraftPick(ILeague league, ILeagueStanding leaguestanding, Map<Integer,IStanding> pickOrderAfterTrading) {
		List<IStanding> standingForAllConference = leaguestanding.getStandingsInAllConference(league);
		IGenerateNewPlayers g = new GenerateNewPlayers();
		List<IPlayer> newPlayers = g.generatePlayers();
		Map<Integer,IStanding> pickOrderBeforeTrading = new HashMap<>();
		IStanding TeamFromTrading; 
		int numberOfTeamsEligibleForPick = 0;
		String positionOfWeakPlayer = " ";

		if (standingForAllConference.size() > 16) {
			numberOfTeamsEligibleForPick = (standingForAllConference.size() - draftPickTeamSubstraction);

		} else {
			numberOfTeamsEligibleForPick = (standingForAllConference.size());
		}

		g.setNumberOfTeams(numberOfTeamsEligibleForPick);
		List<IStanding> teamsEligibleForPick = standingForAllConference.subList(
				standingForAllConference.size() - numberOfTeamsEligibleForPick, standingForAllConference.size() - 1);
		for (int i = 1; i <= 7; i++) {
			 TeamFromTrading = pickOrderAfterTrading.get(i);
			for (int j = teamsEligibleForPick.size() - 1; j >= 0; j--) {
		        if(TeamFromTrading.getTeam().equals(teamsEligibleForPick.get(i).getTeam()))
		        {
		        	
		        }
				List<IPlayer> playersInWeakTeam = teamsEligibleForPick.get(j).getTeam().getPlayerList();
				playersInWeakTeam.sort(Comparator.comparing(IPlayer::getPlayerStrength));
				positionOfWeakPlayer = playersInWeakTeam.get(0).getPosition();
				for (IPlayer player : newPlayers) {
					if (player.getPosition().equals(positionOfWeakPlayer)) {
//						pickOrderBeforeTrading.put(i, teamsEligibleForPick.get(j));
					
//						adjustRoasterAfterDraft()
						newPlayers.remove(player);
						break;
					}
				}
			}
			
		}
	}
}

package com.dhl.g05.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.ILeagueStanding;
import com.dhl.g05.leaguesimulation.IStandingModel;
import com.dhl.g05.team.ITeam;

public class PlayerDraft implements IPlayerDraft {

	private static final int draftPickTeamSubstraction = 16;

	public void playerDraft(ILeagueStanding leaguestanding,Map<Integer, List<IStandingModel>> pickOrderAfterTrading) {
		List<IStandingModel> standingForAllConference = leaguestanding.getRankingAcrossLeague();
		IGenerateNewPlayers g = new GenerateNewPlayers();
		List<IStandingModel> teamsEligibleForPick = new ArrayList<>();
		int numberOfTeamsEligibleForPick = 0;
		if (standingForAllConference.size() > 16) {
			numberOfTeamsEligibleForPick = (standingForAllConference.size() - draftPickTeamSubstraction);
		} else {
			numberOfTeamsEligibleForPick = (standingForAllConference.size());
		}
		g.setNumberOfTeams(numberOfTeamsEligibleForPick);
		List<IPlayer> newPlayers = g.generatePlayers();
		List<IStandingModel> teamsEligibleForPickReverseOrder = standingForAllConference.subList(standingForAllConference.size() - numberOfTeamsEligibleForPick, standingForAllConference.size());
		newPlayers.sort(Comparator.comparing(IPlayer::getPlayerStrength));
		for (int i = teamsEligibleForPickReverseOrder.size() - 1; i >= 0; i--) {
			teamsEligibleForPick.add(teamsEligibleForPickReverseOrder.get(i));
		}
		Map<Integer, List<IStandingModel>> teamOrder= createPickOrder(teamsEligibleForPick, pickOrderAfterTrading);
		pickNewPlayers(teamOrder, newPlayers);
	}

	public Map<Integer, List<IStandingModel>> createPickOrder(List<IStandingModel> teamsEligibleForPick,Map<Integer, List<IStandingModel>> pickOrderAfterTrading) {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		int j = 0;
		for (int i = 1; i <= 7; i++) {
			List<IStandingModel> teamsPickOrder = new ArrayList<>();
			if (pickOrderAfterTrading.get(i) == null) {
			} else {
				for (IStandingModel a : pickOrderAfterTrading.get(i)) {
					teamsPickOrder.add(a);
				}
			}
				for (j = teamsPickOrder.size(); j < teamsEligibleForPick.size(); j++) {
					teamsPickOrder.add(teamsEligibleForPick.get(j));
				}
			
			pickOrder.put(i, teamsPickOrder);
		}
		for (int i = 1; i <= 7; i++) {
			System.out.println();
			System.out.print("Round" + (i) + " Teams ");
			if (pickOrder.get(i) == null) {
				continue;
			} else {
				List<IStandingModel> a = pickOrder.get(i);
				for (IStandingModel standingTeam : a) {
					System.out.print("  " + standingTeam.getTeam().getTeamName());
				}
			}
		}
		return pickOrder;
	}
	
	public void pickNewPlayers(Map<Integer, List<IStandingModel>> teamOrder, List<IPlayer> newPlayers) {
		for (int i = 1; i <= 7; i++) {
			for (IStandingModel team : teamOrder.get(i)) {
				List<IPlayer> playersInTeam = team.getTeam().getPlayerList();
				playersInTeam.add((newPlayers.get(0)));
				newPlayers.remove(0);
				team.getTeam().setPlayerList(playersInTeam);
//				for (IConference c : league.getConferenceDetails()) {
//					for (IDivision d : c.getDivisionDetails()) {
//						for (ITeam t : d.getTeamDetails()) {
//				List<IPlayer> adjustPlayers=team.getTeam().adjustTeamRoasterAfterDraft(team);
//				call roaster;
			}
		}
		List<IStandingModel> a = teamOrder.get(1);
		for (IStandingModel team : a) {
			System.out.println(team.getTeam().getTeamName());
			for (IPlayer player : team.getTeam().getPlayerList()) {
				System.out.println(((FreeAgentModel) player).getPlayerName());
			}
		}
	}
}

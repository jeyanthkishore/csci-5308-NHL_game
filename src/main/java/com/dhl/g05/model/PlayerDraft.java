package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dhl.g05.simulation.ILeagueStanding;
import com.dhl.g05.simulation.IStandingModel;


public class PlayerDraft implements IPlayerDraft {

	private static final int draftPickTeamSubstraction = 2;
	private Map<Integer, List<Map<IStandingModel, IStandingModel>>> pickOrderAfterTrading;

	public Map<Integer, List<Map<IStandingModel, IStandingModel>>> getPickOrderAfterTrading() {
		return pickOrderAfterTrading;
	}

	public void setPickOrderAfterTrading(
			Map<Integer, List<Map<IStandingModel, IStandingModel>>> pickOrderAfterTrading) {
		this.pickOrderAfterTrading = pickOrderAfterTrading;
	}

	public void playerDraft1(ILeagueStanding leaguestanding) {
		List<IStandingModel> standingForAllConference = leaguestanding.getRankingAcrossLeague();

		IGenerateNewPlayers g = new GenerateNewPlayers();
		g.setNumberOfTeams(standingForAllConference.size());
		List<IPlayer> newPlayers = g.generatePlayers();
		newPlayers.sort(Comparator.comparing(IPlayer::getPlayerStrength));

		List<IStandingModel> teamsEligibleForPickFirst = new ArrayList<>();
		List<IStandingModel> teamsEligibleForPickFirstReverseOrder = new ArrayList<>();
		List<IStandingModel> teamsEligibleForPickLater = new ArrayList<>();
		List<IStandingModel> teamsEligibleForPickLaterReverseOrder = new ArrayList<>();
		int numberOfTeamsEligibleForPickFirst = 0;

		numberOfTeamsEligibleForPickFirst = (standingForAllConference.size() - draftPickTeamSubstraction);
		teamsEligibleForPickFirstReverseOrder = standingForAllConference.subList(
				standingForAllConference.size() - numberOfTeamsEligibleForPickFirst, standingForAllConference.size());
		for (int i = teamsEligibleForPickFirstReverseOrder.size() - 1; i >= 0; i--) {
			teamsEligibleForPickFirst.add(teamsEligibleForPickFirstReverseOrder.get(i));
		}
		teamsEligibleForPickLaterReverseOrder = standingForAllConference.subList(0, draftPickTeamSubstraction);
		for (int i = teamsEligibleForPickLaterReverseOrder.size() - 1; i >= 0; i--) {
			teamsEligibleForPickLater.add(teamsEligibleForPickFirstReverseOrder.get(i));
		}
		createPickOrder(teamsEligibleForPickFirst, teamsEligibleForPickLater);

		Map<Integer, List<IStandingModel>> teamOrder = createPickOrder(teamsEligibleForPickFirst,
				teamsEligibleForPickLater);
		pickNewPlayers(teamOrder, newPlayers);
	}

	public Map<Integer, List<IStandingModel>> createPickOrder(List<IStandingModel> teamsEligibleForPickFirst,
			List<IStandingModel> teamsEligibleForPickLater) {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		Map<Integer, List<Map<IStandingModel, IStandingModel>>> pickOrderAfterTrading = getPickOrderAfterTrading();
		for (int i = 1; i <= 7; i++) {
			List<IStandingModel> teamsPickOrder = new ArrayList<>();
			if (pickOrderAfterTrading.get(i) == null) {
				continue;
			} else {
				for (IStandingModel teamToPick : teamsEligibleForPickFirst) {
					int size1 = teamsPickOrder.size();
					List<Map<IStandingModel, IStandingModel>> TeamsTrading = pickOrderAfterTrading.get(i);
					for (Map<IStandingModel, IStandingModel> teamPairTrading : TeamsTrading) {
						for (Map.Entry<IStandingModel, IStandingModel> teamPair : teamPairTrading.entrySet()) {
							if ((teamPair.getKey().getTeam().getTeamName().equals(teamToPick.getTeam().getTeamName()))&& (teamPair.getKey().getConference().getConferenceName().equals(teamToPick.getConference().getConferenceName()))&& (teamPair.getKey().getDivision().getDivisionName().equals(teamToPick.getDivision().getDivisionName()))) {
								teamsPickOrder.add(teamPair.getValue());
								break;
							}
						}
					}
					if (teamsPickOrder.size() == size1) {
						teamsPickOrder.add(teamToPick);
					}
				}
				for (IStandingModel teamToPick : teamsEligibleForPickLater) {
					int size2 = teamsPickOrder.size();
					List<Map<IStandingModel, IStandingModel>> TeamsTrading = pickOrderAfterTrading.get(i);
					for (Map<IStandingModel, IStandingModel> teamPairTrading : TeamsTrading) {
						for (Map.Entry<IStandingModel, IStandingModel> teamPair : teamPairTrading.entrySet()) {
							if ((teamPair.getKey().getTeam().getTeamName().equals(teamToPick.getTeam().getTeamName()))
									&& (teamPair.getKey().getConference().getConferenceName()
											.equals(teamToPick.getConference().getConferenceName()))
									&& (teamPair.getKey().getDivision().getDivisionName()
											.equals(teamToPick.getDivision().getDivisionName()))) {
								teamsPickOrder.add(teamPair.getValue());
								break;
							}
						}
					}
					if (teamsPickOrder.size() == size2) {
						teamsPickOrder.add(teamToPick);
					}
				}
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
		System.out.println("Size of new players are " + newPlayers.size());
		for (int i = 1; i <= 7; i++) {
			for (IStandingModel team : teamOrder.get(i)) {
				List<IPlayer> playersInTeam = team.getTeam().getPlayerList();
				playersInTeam.add((newPlayers.get(0)));
				newPlayers.remove(0);
				team.getTeam().setPlayerList(playersInTeam);
//				call roaster;
			}
		}

			System.out.println("players in "+ teamOrder.get(1).get(1).getTeam().getTeamName());
			for (IPlayer player : teamOrder.get(1).get(1).getTeam().getPlayerList()) {
				System.out.println(((FreeAgentModel) player).getPlayerName());
			}
		}
	

	public void callRoasterToadjustPlayers(IStandingModel team) {

	}
}

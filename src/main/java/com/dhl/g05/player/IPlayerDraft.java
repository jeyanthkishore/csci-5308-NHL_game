package com.dhl.g05.player;

import java.util.List;
import java.util.Map;
import com.dhl.g05.leaguesimulation.ILeagueStanding;
import com.dhl.g05.leaguesimulation.IStandingModel;

public interface IPlayerDraft {

	public void playerDraft(ILeagueStanding leaguestanding, Map<Integer, List<IStandingModel>> pickOrderAfterTrading);

	public Map<Integer, List<IStandingModel>> createPickOrder(List<IStandingModel> teamsEligibleForPick,Map<Integer, List<IStandingModel>> pickOrderAfterTrading);

	public void pickNewPlayers(Map<Integer, List<IStandingModel>> teamOrder, List<IPlayer> newPlayers);

}

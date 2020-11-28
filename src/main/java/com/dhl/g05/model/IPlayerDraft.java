package com.dhl.g05.model;

import java.util.List;
import java.util.Map;

import com.dhl.g05.statemachine.ILeagueStanding;
import com.dhl.g05.statemachine.IStandingModel;

public interface IPlayerDraft {

	public void playerDraft(ILeagueStanding leaguestanding, Map<Integer, List<IStandingModel>> pickOrderAfterTrading);

	public Map<Integer, List<IStandingModel>> createPickOrder(List<IStandingModel> teamsEligibleForPick,Map<Integer, List<IStandingModel>> pickOrderAfterTrading);

	public void pickNewPlayers(Map<Integer, List<IStandingModel>> teamOrder, List<IPlayer> newPlayers);

}

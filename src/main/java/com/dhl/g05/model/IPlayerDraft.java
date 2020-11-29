package com.dhl.g05.model;

import java.util.List;
import java.util.Map;

import com.dhl.g05.simulation.ILeagueStanding;
import com.dhl.g05.simulation.IStandingModel;

public interface IPlayerDraft {

//	public void playerDraft(ILeagueStanding leaguestanding, Map<Integer, List<IStandingModel>> pickOrderAfterTrading);

	public Map<Integer, List<IStandingModel>> createPickOrder(List<IStandingModel> teamsEligibleForPickFirst, List<IStandingModel> teamsEligibleForPickLater);

	public void pickNewPlayers(Map<Integer, List<IStandingModel>> teamOrder, List<IPlayer> newPlayers);

	public void playerDraft1(ILeagueStanding leaguestanding);

	public Map<Integer, List<Map<IStandingModel,IStandingModel>>> getPickOrderAfterTrading();

	public void setPickOrderAfterTrading(Map<Integer, List<Map<IStandingModel, IStandingModel>>> pickOrderAfterTrading);

}

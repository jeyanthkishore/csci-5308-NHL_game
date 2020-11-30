package com.dhl.g05.simulation.statemachine;

import java.util.List;
import java.util.Map;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayerDraft;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;
import com.dhl.g05.trading.TradingAbstractFactory;

public class PlayerDraftState extends AbstractState {

	private ILeague league;
	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		TradingAbstractFactory tradeFactory=ApplicationConfiguration.instance().getTradingConcreteFactoryState();
		IPlayerDraft playerDraft = modelFactory.createPlayerDraft();
		ILeagueStanding standing = league.getLeagueStanding();
		List<IStandingModel> standingList = standing.getRankingAcrossLeague();
		Map<Integer, List<Map<IStandingModel, IStandingModel>>> tradePick = tradeFactory.createTradePickToPlayerDraft().mockTradePickLatest(standingList);
		playerDraft.setPickOrderAfterTrading(tradePick);
		playerDraft.setDraftPickTeamSubstraction(16);
		playerDraft.playerDraft1(standing);
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createAdvanceToNextSeasonState());
		return true;
	}

}

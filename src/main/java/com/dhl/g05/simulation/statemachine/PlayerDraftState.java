package com.dhl.g05.simulation.statemachine;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayerDraft;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;
import com.dhl.g05.trading.TradingAbstractFactory;

public class PlayerDraftState extends AbstractState {

	static final Logger logger = LogManager.getLogger(PlayerDraftState.class);
	private ILeague league;
	
	@Override
	public boolean enter() {
		logger.info("Entering into PlayerDraftState");
		
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		logger.info("Simulating PlayerDraftState");
		
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		TradingAbstractFactory tradeFactory=ApplicationConfiguration.instance().getTradingConcreteFactoryState();
		IPlayerDraft playerDraft = modelFactory.createPlayerDraft();
		ILeagueStanding standing = league.getLeagueStanding();
		List<IStandingModel> standingList = standing.getRankingAcrossLeague();
		Map<Integer, List<Map<IStandingModel, IStandingModel>>> tradePick = tradeFactory.createTradePickToPlayerDraft().mockTradePickLatest(standingList);
		playerDraft.setPickOrderAfterTrading(tradePick);
		playerDraft.setDraftPickTeamSubstraction(4);
		playerDraft.playerDraft1(standing);
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting PlayerDraftState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createAdvanceToNextSeasonState());
		return true;
	}

}

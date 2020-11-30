package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.trading.IIntiateTradeOffer;
import com.dhl.g05.trading.TradingAbstractFactory;

public class TradeState extends AbstractState {
	
	static final Logger logger = LogManager.getLogger(TradeState.class);
	private ILeague league;

	@Override
	public boolean enter() {
		logger.info("Entering into TradeState");
		
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		logger.info("Simulating Trade Process");
		
		TradingAbstractFactory tradeFactory = ApplicationConfiguration.instance().getTradingConcreteFactoryState();
		IIntiateTradeOffer tradeClass = tradeFactory.createInititatetradeoffer();
		tradeClass.setTrade(league.getGamePlayConfig().getTradingConfig());
		tradeClass.initiateTradeOffer(league);
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting TradeState");
		
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createAgingState());
		return true;
	}

}

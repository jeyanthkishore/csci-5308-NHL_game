package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.trading.IIntiateTradeOffer;
import com.dhl.g05.trading.TradingAbstractFactory;

public class TradeState extends AbstractState {
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		TradingAbstractFactory tradeFactory = ApplicationConfiguration.instance().getTradingConcreteFactoryState();
		IIntiateTradeOffer tradeClass = tradeFactory.createInititatetradeoffer();
		tradeClass.setTrade(league.getGamePlayConfig().getTradingConfig());
		tradeClass.initiateTradeOffer(league);
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance()
				.getSimulationConcreteFactoryState();
		this.setNextState(stateFactory.createAgingState());
		return true;
	}

}

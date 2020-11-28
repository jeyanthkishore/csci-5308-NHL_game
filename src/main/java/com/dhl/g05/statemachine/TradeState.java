package com.dhl.g05.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.trading.IIntiateTradeOffer;
import com.dhl.g05.trading.InitiateTradeOffer;

public class TradeState extends AbstractState{
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		IIntiateTradeOffer tradeClass = new InitiateTradeOffer();
		tradeClass.setTrade(league.getGamePlayConfig().getTrading());
		tradeClass.initiateTradeOffer(league);
        return true;
	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		this.setNextState(stateFactory.createAgingState());
		return true;
	}

}

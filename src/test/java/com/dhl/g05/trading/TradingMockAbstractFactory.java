package com.dhl.g05.trading;

public abstract class TradingMockAbstractFactory {

	private static TradingMockAbstractFactory tradingMockAbstractFactory;

	public static TradingMockAbstractFactory instance(TradingMockState state) {
		tradingMockAbstractFactory = state.concreteMethod();
		return tradingMockAbstractFactory;
	}

	public abstract MockLeagueModel createMockLeagueModel();
	public abstract WeakTeamTest createWeakTeamTest();

}

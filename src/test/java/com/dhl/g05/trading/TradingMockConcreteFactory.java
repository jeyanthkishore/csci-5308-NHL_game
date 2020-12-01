package com.dhl.g05.trading;

public class TradingMockConcreteFactory extends TradingMockAbstractFactory{

	@Override
	public MockLeagueModel createMockLeagueModel() {
		return new MockLeagueModel();
	}

	@Override
	public WeakTeamTest createWeakTeamTest() {
		return new WeakTeamTest();
	}
}

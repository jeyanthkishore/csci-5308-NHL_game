package com.dhl.g05.trading;

public class TradingMockConcreteFactoryState extends TradingMockState {

	@Override
	public TradingMockAbstractFactory concreteMethod() {
		return new TradingMockConcreteFactory();
	}

}

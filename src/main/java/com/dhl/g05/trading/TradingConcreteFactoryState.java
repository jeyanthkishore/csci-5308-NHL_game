package com.dhl.g05.trading;

public class TradingConcreteFactoryState extends TradingState {

	@Override
	public TradingAbstractFactory concreteMethod() {
		return new TradingConcreteFactory();
	}

}

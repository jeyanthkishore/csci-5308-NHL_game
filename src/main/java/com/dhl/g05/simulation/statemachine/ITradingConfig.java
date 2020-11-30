package com.dhl.g05.simulation.statemachine;

public interface ITradingConfig {
	 
	public int getLossPoint();

	public void setLossPoint(int lossPoint);

	public double getRandomTradeOfferChance();

	public void setRandomTradeOfferChance(double d);

	public int getMaxPlayersPerTrade();

	public void setMaxPlayersPerTrade(int maxPlayersPerTrade);

	public double getRandomAcceptanceChance();

	public void setRandomAcceptanceChance(double d);

	public TradingConstant validate();

	public boolean isNotValidLossPoint();

	public boolean isNotValidTradeOfferChance();

	public boolean isNotValidMaxplayerPerTrade();

	public boolean isNotValidAcceptanceChance();

}

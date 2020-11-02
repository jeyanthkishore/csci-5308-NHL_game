package com.dhl.g05.gameplayconfig;

public class TradingModel implements ITradingModel{

	private int lossPoint;
    private double randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private double randomAcceptanceChance;
    
    public TradingModel(int loss,double tradeOffer,int maxPlayer,double accept) {
    	this.lossPoint = loss;
    	this.randomTradeOfferChance = tradeOffer;
    	this.maxPlayersPerTrade = maxPlayer;
    	this.randomAcceptanceChance = accept;
    }

    public TradingModel() {
	}

	public int getLossPoint() {
        return lossPoint;
    }

    public void setLossPoint(int lossPoint) {
        this.lossPoint = lossPoint;
    }

    public double getRandomTradeOfferChance() {
        return randomTradeOfferChance;
    }

    public void setRandomTradeOfferChance(double d) {
        this.randomTradeOfferChance = d;
    }

    public int getMaxPlayersPerTrade() {
        return maxPlayersPerTrade;
    }

    public void setMaxPlayersPerTrade(int maxPlayersPerTrade) {
        this.maxPlayersPerTrade = maxPlayersPerTrade;
    }

    public double getRandomAcceptanceChance() {
        return randomAcceptanceChance;
    }

    public void setRandomAcceptanceChance(double d) {
        this.randomAcceptanceChance = d;
    }

	public boolean isValidLossPoint(int lossPoint) {
		return lossPoint >= 0;
	}

	public boolean isValidTradeOfferChance(double TradeOfferChance) {
		return TradeOfferChance >= 0 && TradeOfferChance <= 1;
	}

	public boolean isValidMaxplayerPerTrade(int maxPlayerEntry) {
		return maxPlayerEntry >= 0;
	}

	public boolean isValidAcceptanceChance(double randomAcceptanceChance) {
		return randomAcceptanceChance >= 0 && randomAcceptanceChance <= 1;
	}
}

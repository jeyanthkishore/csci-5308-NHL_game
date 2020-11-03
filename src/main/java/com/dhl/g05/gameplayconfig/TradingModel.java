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
    public TradingConstant validate() {
    	if(isNotValidLossPoint()) {
    		return TradingConstant.LossError;
    	}
    	if(isNotValidTradeOfferChance()) {
    		return TradingConstant.OfferError;
    	}
    	if(isNotValidMaxplayerPerTrade()) {
    		return TradingConstant.MaxPlayerError;
    	}
    	if(isNotValidAcceptanceChance()) {
    		return TradingConstant.AcceptanceError;
    	}
		return TradingConstant.Success;
    }
    
    
	public boolean isNotValidLossPoint() {
		return lossPoint < 0;
	}

	public boolean isNotValidTradeOfferChance() {
		return randomTradeOfferChance < 0 || randomTradeOfferChance > 1;
	}

	public boolean isNotValidMaxplayerPerTrade() {
		return maxPlayersPerTrade < 0;
	}

	public boolean isNotValidAcceptanceChance() {
		return randomAcceptanceChance < 0 || randomAcceptanceChance > 1;
	}
}

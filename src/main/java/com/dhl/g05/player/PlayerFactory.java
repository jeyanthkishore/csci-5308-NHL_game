package com.dhl.g05.player;

public class PlayerFactory extends AbstractPlayerFactory{

    @Override
    public IPlayer getPLayer() {
        return new PlayerModel();
    }

    @Override
    public IRandomNumberFactory getRandomNumber() {
        return new RandomNumberFactory();
    }

    @Override
    public IPlayerInjured getPlayerInjury() {
        return new PlayerInjury();
    }

    @Override
    public IPlayerRetired getPlayerRetirement() {
        return new PlayerRetirement();
    }

}

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

    @Override
    public IPlayerRetirement getRetiredPlayer() {
        return new PlayerModel();
    }

    @Override
    public IPlayerInjury getInjuredPlayer() {
        return new PlayerModel();
    }
    
	@Override
	public IGenerateNewPlayers getGenerateNewPlayers() {
		return new GenerateNewPlayers();
	}

	@Override
	public IPlayerBirthday getPlayerBirthday() {
		return new PlayerBirthday();
	}



}

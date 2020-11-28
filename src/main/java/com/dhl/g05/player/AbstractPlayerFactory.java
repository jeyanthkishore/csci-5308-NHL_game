package com.dhl.g05.player;

public abstract class AbstractPlayerFactory {

    public static AbstractPlayerFactory abstractPlayerFactory;

    public static AbstractPlayerFactory getFactory() {
        return abstractPlayerFactory;
    }

    public static void setFactory(AbstractPlayerFactory abstractPlayerFactory) {
        AbstractPlayerFactory.abstractPlayerFactory = abstractPlayerFactory;
    }

    public abstract IPlayer getPlayer();

    public abstract  IPlayer getPlayer(String playerName, String position, Boolean captain,double skating, double shooting, double checking, double saving, int birthDay,int birthMonth,int birthYear);

    public abstract IRandomNumberFactory getRandomNumber();

    public abstract  IPlayerInjured getPlayerInjury();

    public abstract IPlayerRetired getPlayerRetirement();

    public abstract IPlayerRetirement getRetiredPlayer();

    public abstract IPlayerInjury getInjuredPlayer();
    
    public abstract IGenerateNewPlayers getGenerateNewPlayers();
    
    public abstract IPlayerBirthday getPlayerBirthday();

}

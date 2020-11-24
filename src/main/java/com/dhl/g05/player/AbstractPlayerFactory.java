package com.dhl.g05.player;

public abstract class AbstractPlayerFactory {

    public static AbstractPlayerFactory abstractPlayerFactory;

    public static AbstractPlayerFactory getFactory() {
        return abstractPlayerFactory;
    }

    public static void setFactory(AbstractPlayerFactory abstractPlayerFactory) {
        AbstractPlayerFactory.abstractPlayerFactory = abstractPlayerFactory;
    }

    public abstract IPlayer getPLayer();

    public abstract IRandomNumberFactory getRandomNumber();

    public abstract  IPlayerInjured getPlayerInjury();

    public abstract IPlayerRetired getPlayerRetirement();

}

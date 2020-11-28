package com.dhl.g05.gameplayconfig;

public abstract class AbstractGamePlayConfigFactory {

    private static AbstractGamePlayConfigFactory abstractGamePlayConfigFactory;

    public static AbstractGamePlayConfigFactory getFactory() {
        return abstractGamePlayConfigFactory;
    }

    public static void setFactory(AbstractGamePlayConfigFactory abstractGamePlayConfigFactory) {
        AbstractGamePlayConfigFactory.abstractGamePlayConfigFactory = abstractGamePlayConfigFactory;
    }

    public abstract IAging getAging();

    public abstract  IInjury getInjury();

    public abstract ITradingConfig getTradingConfig();

    public abstract ITraining getTraining();
}

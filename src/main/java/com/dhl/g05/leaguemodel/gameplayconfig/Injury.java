package com.dhl.g05.leaguemodel.gameplayconfig;

public class Injury implements IInjury {
    private double randomInjuryChance;
    private int injuryDaysLow;
    private int injuryDaysHigh;

    public Injury() {
    }

    public Injury(double randomInjuryChance, int injuryDaysLow, int injuryDaysHigh) {
        this.randomInjuryChance = randomInjuryChance;
        this.injuryDaysLow = injuryDaysLow;
        this.injuryDaysHigh = injuryDaysHigh;
    }

    @Override
    public double getRandomInjuryChance() {
        return randomInjuryChance;
    }

    @Override
    public void setRandomInjuryChance(double randomInjuryChance) {
        this.randomInjuryChance = randomInjuryChance;
    }

    @Override
    public int getInjuryDaysLow() {
        return injuryDaysLow;
    }

    @Override
    public void setInjuryDaysLow(int injuryDaysLow) {
        this.injuryDaysLow = injuryDaysLow;
    }

    @Override
    public int getInjuryDaysHigh() {
        return injuryDaysHigh;
    }

    @Override
    public void setInjuryDaysHigh(int injuryDaysHigh) {
        this.injuryDaysHigh = injuryDaysHigh;
    }

}

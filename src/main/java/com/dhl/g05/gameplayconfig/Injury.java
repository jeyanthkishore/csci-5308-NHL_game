package com.dhl.g05.gameplayconfig;

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

    public InjuryConstant validate() {
        if(isRandomInjuryChanceNotValid(randomInjuryChance)){
            return InjuryConstant.RandomInjuryChanceError;
        }
        if(isInjuryDaysHighValueNotValid(injuryDaysLow, injuryDaysHigh)) {
            return InjuryConstant.InjuryDaysError;
        }
        return InjuryConstant.Success;
    }

    public boolean isRandomInjuryChanceNotValid(double randomInjuryChance) {
        if (randomInjuryChance < -1) {
            return true;
        }
        return false;
    }

    public boolean isInjuryDaysHighValueNotValid(int injuryDaysLow,int injuryDaysHigh) {
        if(injuryDaysHigh <= injuryDaysLow) {
            return true;
        }
        return false;
    }
}

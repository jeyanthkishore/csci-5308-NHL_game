package com.dhl.g05.simulation.statemachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.annotations.Expose;

public class InjuryConfig implements IInjury {

    static final Logger logger = LogManager.getLogger(InjuryConfig.class);
    private int MIN_RANDOM_INJURY_CHANCE = -1;
    @Expose
    private double randomInjuryChance;
    @Expose
    private int injuryDaysLow;
    @Expose
    private int injuryDaysHigh;

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

    @Override
    public InjuryConstant validate() {
        logger.info("Validating injury details");
        if(isRandomInjuryChanceNotValid(randomInjuryChance)){
            return InjuryConstant.RandomInjuryChanceError;
        }
        if(isInjuryDaysHighValueNotValid(injuryDaysLow, injuryDaysHigh)) {
            return InjuryConstant.InjuryDaysError;
        }
        return InjuryConstant.Success;
    }

    private boolean isRandomInjuryChanceNotValid(double randomInjuryChance) {
        if (randomInjuryChance > MIN_RANDOM_INJURY_CHANCE) {
            return false;
        }
        return true;
    }

    private boolean isInjuryDaysHighValueNotValid(int injuryDaysLow, int injuryDaysHigh) {
        if(injuryDaysHigh <= injuryDaysLow) {
            return true;
        }
        return false;
    }
}

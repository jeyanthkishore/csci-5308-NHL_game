package com.dhl.g05.simulation.statemachine;

public interface IInjury {

    double getRandomInjuryChance();

    void setRandomInjuryChance(double randomInjuryChance);

    int getInjuryDaysLow();

    void setInjuryDaysLow(int injuryDaysLow);

    int getInjuryDaysHigh();

    void setInjuryDaysHigh(int injuryDaysHigh);

    InjuryConstant validate();

}

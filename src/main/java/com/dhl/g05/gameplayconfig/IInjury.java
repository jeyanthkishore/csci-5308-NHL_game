package com.dhl.g05.gameplayconfig;

public interface IInjury {

    double getRandomInjuryChance();

    void setRandomInjuryChance(double randomInjuryChance);

    int getInjuryDaysLow();

    void setInjuryDaysLow(int injuryDaysLow);

    int getInjuryDaysHigh();

    void setInjuryDaysHigh(int injuryDaysHigh);

}

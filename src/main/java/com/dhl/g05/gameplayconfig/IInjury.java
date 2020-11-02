package com.dhl.g05.gameplayconfig;

public interface IInjury {
    public double getRandomInjuryChance();
    public void setRandomInjuryChance(double randomInjuryChance);

    public int getInjuryDaysLow();
    public void setInjuryDaysLow(int injuryDaysLow);

    public int getInjuryDaysHigh();
    public void setInjuryDaysHigh(int injuryDaysHigh);
}

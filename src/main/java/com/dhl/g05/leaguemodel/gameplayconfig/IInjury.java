package com.dhl.g05.leaguemodel.gameplayconfig;

public interface IInjury {
    public double getRandomInjuryChance();

    public void setRandomInjuryChance(double randomInjuryChance);

    public int getInjuryDaysLow();

    public void setInjuryDaysLow(int injuryDaysLow);

    public int getInjuryDaysHigh();

    public void setInjuryDaysHigh(int injuryDaysHigh);
}

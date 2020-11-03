package com.dhl.g05.player;

import com.dhl.g05.freeagent.FreeAgentConstant;

import java.time.LocalDate;

public interface IPlayer {

    Boolean getCaptain();

    void setCaptain(Boolean captain);

    int getInjuredForNumberOfDays();

    void setInjuredForNumberOfDays(int injuredForNumberOfDays);

    int getElapsedDaysSinceLastBDay();

    void setElapsedDaysSinceLastBDay(int elapsedDaysSinceLastBDay);

    void calculatePlayerAgeByDays(int days);

    boolean isRecovered(IPlayerProgress playerCareerProgression, LocalDate currentDate);

    LocalDate getInjuryDate();

    void setInjuryDate(LocalDate injuryDate);

}

package com.dhl.g05.player;

import com.dhl.g05.freeagent.FreeAgentConstant;

import java.time.LocalDate;

public interface IPlayer {

    Boolean getCaptain();

    void setCaptain(Boolean captain);

    int getInjuredForNumberOfDays();

    void setInjuredForNumberOfDays(int injuredForNumberOfDays);
}

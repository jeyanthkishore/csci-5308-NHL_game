package com.dhl.g05.simulation.statemachine;

public interface IGameStatConfig {

    double getPenaltyChance();

    void setPenaltyChance(double penaltyChance);

    GameStatConfigConstant validate();

}

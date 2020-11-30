package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameStatConfig implements IGameStatConfig{

    static final Logger logger = LogManager.getLogger(GameStatConfig.class);
    private double penaltyChance;
    private int MIN_PENALTY_CHANCE = 0;
    private int MAX_PENALTY_CHANCE = 1;

    @Override
    public double getPenaltyChance() {
        return penaltyChance;
    }

    @Override
    public void setPenaltyChance(double penaltyChance) {
        this.penaltyChance = penaltyChance;
    }

    @Override
    public GameStatConfigConstant validate() {
        logger.info("Validating GameStatConfig values");
        if(isPenaltyChanceNotValid(penaltyChance)) {
            return GameStatConfigConstant.PenaltyChanceNotValid;
        }
        return GameStatConfigConstant.Success;
    }

    private boolean isPenaltyChanceNotValid(double penaltyChance) {
        if(penaltyChance >= MIN_PENALTY_CHANCE && penaltyChance <= MAX_PENALTY_CHANCE) {
            return false;
        }
        return true;
    }

}

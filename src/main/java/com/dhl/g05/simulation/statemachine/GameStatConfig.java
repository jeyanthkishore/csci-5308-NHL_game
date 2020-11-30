package com.dhl.g05.simulation.statemachine;

public class GameStatConfig implements IGameStatConfig{

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

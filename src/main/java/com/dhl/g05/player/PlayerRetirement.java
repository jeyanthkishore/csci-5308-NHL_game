package com.dhl.g05.player;

import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;

public class PlayerRetirement implements IPlayerRetired{

    @Override
    public boolean checkPlayerRetirement(IAging aging, IFreeAgent player) {
        IRandomNumberFactory randomNumberFactory = new RandomNumberFactory();
        if(player.getAge() > aging.getMaximumAge()) {
            player.setRetiredStatus(true);
            return true;
        }
        else {
            if (player.getAge() < aging.getAverageRetirementAge()) {
                int likelihood = randomNumberFactory.generateRandomIntegerNumber(15,0);
                int randomAge = randomNumberFactory.generateRandomIntegerNumber(aging.getAverageRetirementAge(),0);
                if (randomAge < likelihood) {
                    player.setRetiredStatus(true);
                    return true;
                }
                else {
                    player.setRetiredStatus(false);
                    return false;
                }
            }
            else {
                int likelihood = randomNumberFactory.generateRandomIntegerNumber(50,16);
                int randomAge = randomNumberFactory.generateRandomIntegerNumber(aging.getMaximumAge(),aging.getAverageRetirementAge());
                if (randomAge < likelihood) {
                    player.setRetiredStatus(true);
                    return true;
                }
                else {
                    player.setRetiredStatus(false);
                    return false;
                }
            }
        }
    }
}

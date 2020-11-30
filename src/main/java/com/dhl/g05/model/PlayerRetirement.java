package com.dhl.g05.model;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.statemachine.IAging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerRetirement implements IPlayerRetired{

    static final Logger logger = LogManager.getLogger(PlayerRetirement.class);

    @Override
    public boolean checkPlayerRetirement(IAging aging, IPlayer player) {
        logger.info("check whether player is retired or not");
        IRandomNumberFactory randomNumberFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState().createRandomNumber();
        if(player.getAge() > aging.getMaximumAge()) {
            player.setRetirementStatus(true);
            return true;
        }
        else {
            if (player.getAge() < aging.getAverageRetirementAge()) {
                int likelihood = randomNumberFactory.generateRandomIntegerNumber(15,0);
                int randomAge = randomNumberFactory.generateRandomIntegerNumber(aging.getAverageRetirementAge(),0);
                if (randomAge < likelihood) {
                    player.setRetirementStatus(true);
                    return true;
                }
                else {
                    player.setRetirementStatus(false);
                    return false;
                }
            }
            else {
                int likelihood = randomNumberFactory.generateRandomIntegerNumber(50,16);
                int randomAge = randomNumberFactory.generateRandomIntegerNumber(aging.getMaximumAge(),aging.getAverageRetirementAge());
                if (randomAge < likelihood) {
                    player.setRetirementStatus(true);
                    return true;
                }
                else {
                    player.setRetirementStatus(false);
                    return false;
                }
            }
        }
    }

}

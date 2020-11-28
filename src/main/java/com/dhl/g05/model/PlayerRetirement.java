package com.dhl.g05.model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.statemachine.IAging;

public class PlayerRetirement implements IPlayerRetired{
    static final Logger logger = LogManager.getLogger(PlayerRetirement.class);

    @Override
    public boolean checkPlayerRetirement(IAging aging, IPlayer player) {
        logger.info("check whether player is retired or not");
        IRandomNumberFactory randomNumberFactory = AbstractPlayerFactory.getFactory().getRandomNumber();
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

    @Override
    public boolean isFreeAgentsRetired(ILeague league, IFreeAgent freeAgent) {
        logger.info("Getting status of retirement of freeAgents");
        boolean isFreeAgentRemovedFromLeague = league.removeRetiredFreeAgentsFromLeague(freeAgent);
        if (isFreeAgentRemovedFromLeague) {
            league.addRetiredFreeAgentToList(freeAgent);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayerRetired(ILeague league, IPlayer player, ITeam team) {
        logger.info("Getting status of retirement of players");
        boolean isPlayerRemovedFromTeam = team.removeRetiredPlayerFromTeam(player);
        if (isPlayerRemovedFromTeam) {
            league.addRetiredPlayersToList(player);
            return true;
        }
        return false;
    }

}

package com.dhl.g05.player;

import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

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

    @Override
    public boolean isFreeAgentsRetired(ILeague league, IFreeAgent freeAgent) {
        boolean isFreeAgentRemovedFromLeague = league.removeRetiredFreeAgentsFromLeague(freeAgent);
        if (isFreeAgentRemovedFromLeague) {
            league.addRetiredFreeAgentToList(freeAgent);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayerRetired(ILeague league, IPlayer player, ITeam team) {
        boolean isPlayerRemovedFromTeam = team.removeRetiredPlayerFromTeam(player);
        if (isPlayerRemovedFromTeam) {
            league.addRetiredPlayersToList(player);
            return true;
        }
        return false;
    }

}

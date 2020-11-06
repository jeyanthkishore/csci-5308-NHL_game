package com.dhl.g05.player;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerInjury implements IPlayerInjured {

    @Override
    public boolean isPlayerInjured(playerInjury playerObject, IInjury injury){
        double randomInjuryChance= injury.getRandomInjuryChance();
        int maximumInjuryDays= injury.getInjuryDaysHigh();
        int minimumInjuryDays= injury.getInjuryDaysLow();
        if (playerObject.getInjuredStatus()){
            return true;
        }
        else {
            double injuryChance =generateRandomDoubleNumber(0, 1);
            if (injuryChance < randomInjuryChance){
                int injuredForNumberOfDays = generateRandomIntegerNumber(minimumInjuryDays,maximumInjuryDays);
                playerObject.setInjuredForNumberOfDays(injuredForNumberOfDays);
                playerObject.setInjuredStatus(true);
                return true;
            }
            return false;
        }
    }

    private double generateRandomDoubleNumber(high,low) {
        return (double) ((Math.random() * (high - low)) + high)
    }

    private int generateRandomIntegerNumber(high,low) {
        return (int) ((Math.random() * (high - low)) + high)
    }
}


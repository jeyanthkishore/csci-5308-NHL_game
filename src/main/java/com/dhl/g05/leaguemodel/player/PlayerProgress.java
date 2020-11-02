package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.freeagent.IFreeAgent;
import com.dhl.g05.leaguemodel.gameplayconfig.IInjury;

public class PlayerProgress implements IPlayerProgress{
    private final int MAX_INJURY_PROBABILITY = 1;
    private final int MIN_INJURY_PROBABILITY = 0;
    private final int INJURY_ROUND_DECIMAL = 2;
    private IRandomGeneratorFactory randomGeneratorFactory;

    public PlayerProgress(IRandomGeneratorFactory randomGeneratorFactory) {
        this.randomGeneratorFactory = randomGeneratorFactory;
    }

    public boolean isInjured(PlayerModel playerObject, IInjury injury){
        double randomInjuryChance= injury.getRandomInjuryChance();
        int maximumInjuryDays= injury.getInjuryDaysHigh();
        int minimumInjuryDays= injury.getInjuryDaysLow();
        if (playerObject.getInjuredStatus()){
            return true;
        }
        else {
            double injuryProbability =randomGeneratorFactory.getRandomDoubleNumber(MAX_INJURY_PROBABILITY, MIN_INJURY_PROBABILITY);
            double roundedInjuryProbability = randomGeneratorFactory.roundDoubleNumber(injuryProbability,INJURY_ROUND_DECIMAL);
            if (roundedInjuryProbability < randomInjuryChance){
                int injuredForNumberOfDays = randomGeneratorFactory.getRandomIntegerNumber(minimumInjuryDays,maximumInjuryDays);
                playerObject.setInjuredForNumberOfDays(injuredForNumberOfDays);
                playerObject.setInjuredStatus(true);
                return true;
            }
            return false;
        }
    }
}


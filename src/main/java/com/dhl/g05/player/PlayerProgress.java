package com.dhl.g05.player;


import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;

public class PlayerProgress implements IPlayerProgress{
    private final int MAX_INJURY_PROBABILITY = 1;
    private final int MIN_INJURY_PROBABILITY = 0;
    private final int INJURY_ROUND_DECIMAL = 2;
    private final int DAYS_IN_YEAR = 365;
    private IRandomGeneratorFactory randomGeneratorFactory;

    public PlayerProgress(IRandomGeneratorFactory randomGeneratorFactory) {
        this.randomGeneratorFactory = randomGeneratorFactory;
    }

    @Override
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

    @Override
    public boolean isRetired(PlayerModel player, IAging aging) {
        int playerAgeInDays = (player.getAge() * DAYS_IN_YEAR) + player.getElapsedDaysSinceLastBDay();
        int maximumAgeOfPlayerInDays = (aging.getMaximumAge() * DAYS_IN_YEAR);
        int averageRetirementAgeInDays = (aging.getAverageRetirementAge() * DAYS_IN_YEAR);

        if ( playerAgeInDays >= maximumAgeOfPlayerInDays) {
            player.setRetiredStatus(true);
            return true;
        }

        double randomRetirementProbability = randomGeneratorFactory.getRandomDoubleNumber(0, aging.getMaximumAge());
        double retirementLikeliehood;
        if (playerAgeInDays <= averageRetirementAgeInDays) {
            retirementLikeliehood = 1 / ((double) (averageRetirementAgeInDays - playerAgeInDays) / (double) DAYS_IN_YEAR) * ((double) aging.getAverageRetirementAge() / 10);
        }
        else {
            retirementLikeliehood = 1 / ((double) (playerAgeInDays - averageRetirementAgeInDays) / (double) DAYS_IN_YEAR) * aging.getAverageRetirementAge();
        }
        if(randomRetirementProbability < retirementLikeliehood) {
            player.setRetiredStatus(true);
            return true;
        }
        return false;
    }
}


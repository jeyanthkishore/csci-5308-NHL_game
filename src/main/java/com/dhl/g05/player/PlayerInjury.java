package com.dhl.g05.player;
import com.dhl.g05.gameplayconfig.IInjury;

public class PlayerInjury implements IPlayerInjured {

    @Override
    public boolean checkPlayerInjury(IPlayer player, IInjury injury) {
        IRandomNumberFactory randomNumberFactory = new RandomNumberFactory();
        if(player.getInjuredStatus()) {
            return true;
        }
        else {
            double randomDecimal = randomNumberFactory.generateRandomDoubleNumber(0,1);
            if (randomDecimal < injury.getRandomInjuryChance()) {
                int injuredForNumberOfDays = randomNumberFactory.generateRandomIntegerNumber(injury.getInjuryDaysLow(),injury.getInjuryDaysHigh());
                player.setInjuredForNumberOfDays(injuredForNumberOfDays);
                player.setInjuredStatus(true);
                return true;
            }
            return false;
        }
    }
}


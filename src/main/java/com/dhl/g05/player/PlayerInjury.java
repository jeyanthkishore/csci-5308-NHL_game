package com.dhl.g05.player;
import com.dhl.g05.gameplayconfig.IInjury;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerInjury implements IPlayerInjured {
    static final Logger logger = LogManager.getLogger(PlayerInjury.class);

    @Override
    public boolean checkPlayerInjury(IPlayer player, IInjury injury) {
        logger.info("Check whether player is injured or not");
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


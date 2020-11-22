package com.dhl.g05.player;
import com.dhl.g05.gameplayconfig.IInjury;

public class PlayerInjury implements IPlayerInjured {
    public PlayerInjury() {
    }

    @Override
    public boolean isPlayerInjured(IPlayer playerObject, IInjury injury){
        double randomInjuryChance= injury.getRandomInjuryChance();
        int maximumInjuryDays= injury.getInjuryDaysHigh();
        int minimumInjuryDays= injury.getInjuryDaysLow();
        if (playerObject.getInjuredStatus()){
            return true;
        }
        else {
            double injuryChance = generateRandomDoubleNumber(0,1);
            if (injuryChance < randomInjuryChance){
                int injuredForNumberOfDays = generateRandomIntegerNumber(minimumInjuryDays,maximumInjuryDays);
                playerObject.setInjuredForNumberOfDays(injuredForNumberOfDays);
                playerObject.setInjuredStatus(true);
                return true;
            }
            return false;
        }
    }

    private double generateRandomDoubleNumber(double high,double low) {
        return (double) ((Math.random() * (high - low)) + high);
    }

    private int generateRandomIntegerNumber(int high,int low) {
        return (int) ((Math.random() * (high - low)) + high);
    }
}


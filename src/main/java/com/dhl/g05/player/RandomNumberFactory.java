package com.dhl.g05.player;
import java.util.Random;

public class RandomNumberFactory implements IRandomNumberFactory{

    @Override
    public double generateRandomDoubleNumber(double high,double low) {
        Random random = new Random();
        double randomValue = low + (high - low) * random.nextDouble();
        String formatNumber = String.format("%1.2f", randomValue);
        return Double.parseDouble(formatNumber);
    }

    //Reference: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    @Override
    public int generateRandomIntegerNumber(int high,int low) {
        return low + (int)(Math.random() * ((high - low) + 1));
    }
}

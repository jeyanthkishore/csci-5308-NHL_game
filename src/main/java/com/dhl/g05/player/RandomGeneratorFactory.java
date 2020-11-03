package com.dhl.g05.player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RandomGeneratorFactory implements IRandomGeneratorFactory{
    private Random random;
    public RandomGeneratorFactory() {
        random = new Random();
    }

    @Override
    public int getRandomIntegerNumber(int minimum, int maximum) {
        if (maximum < minimum) {
            return -1;
        }
        int randomNumber = random.nextInt(maximum+1);
        if(randomNumber < minimum) {
            randomNumber += minimum;
        }
        return randomNumber;
    }

    @Override
    public double getRandomDoubleNumber(int minimum, int maximum) {
        if (maximum < minimum) {
            return -1;
        }
        return (random.nextDouble() * (maximum - minimum)) + minimum;
    }

    @Override
    public double roundDoubleNumber(double number, int decimalPlaces) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number)).setScale(decimalPlaces, RoundingMode.CEILING);
        return bigDecimal.doubleValue();
    }
}

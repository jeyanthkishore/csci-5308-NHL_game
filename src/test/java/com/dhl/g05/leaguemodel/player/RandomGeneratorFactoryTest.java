package com.dhl.g05.leaguemodel.player;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class RandomGeneratorFactoryTest {

    @Test
    public void getRandomIntegerNumberTest() {
        IRandomGeneratorFactory randomGeneratorFactory = new RandomGeneratorFactory();
        int randomNumber = randomGeneratorFactory.getRandomIntegerNumber(1, 50);
        Assert.assertTrue(randomNumber >= 1 && randomNumber <= 50);
        randomNumber = randomGeneratorFactory.getRandomIntegerNumber(0, 1);
        Assert.assertTrue(randomNumber >= 0 && randomNumber <= 1);
        Assert.assertEquals(-1, randomGeneratorFactory.getRandomIntegerNumber(20, 1), 0.0);
    }

    @Test
    public void getRandomDoubleNumberTest() {
        IRandomGeneratorFactory randomGeneratorFactory = new RandomGeneratorFactory();
        double randomNumber = randomGeneratorFactory.getRandomDoubleNumber(1, 50);
        Assert.assertTrue(randomNumber >= 1 && randomNumber <= 50);
        Assert.assertEquals(-1, randomGeneratorFactory.getRandomDoubleNumber(20, 1), 0.0);
    }

    @Test
    public void roundDoubleNumberTest() {
        IRandomGeneratorFactory randomGeneratorFactory = new RandomGeneratorFactory();
        double roundedNumber = randomGeneratorFactory.roundDoubleNumber(25.5010, 2);
        BigDecimal bigDecimal = new BigDecimal(Double.toString(roundedNumber));
        Assert.assertEquals(2, bigDecimal.stripTrailingZeros().scale());
    }




}

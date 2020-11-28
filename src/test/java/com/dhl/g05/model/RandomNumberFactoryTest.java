package com.dhl.g05.model;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.IRandomNumberFactory;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.model.RandomNumberFactory;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RandomNumberFactoryTest {

    private static AbstractPlayerFactory playerFactory;

    @BeforeClass
    public static void setup() {
        AbstractPlayerFactory.setFactory(new PlayerFactory());
        playerFactory = AbstractPlayerFactory.getFactory();
    }

    @Test
    public void generateRandomDoubleNumberTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IRandomNumberFactory randomNumberFactory = playerFactory.getRandomNumber();
        when(randomNumberFactoryMock.generateRandomDoubleNumber(1,10)).thenReturn(5.6);
        double randomDecimal = randomNumberFactory.generateRandomDoubleNumber(1,10);
        assertTrue(randomDecimal>=1 && randomDecimal<=10);
    }

    @Test
    public void generateRandomIntegerNumberTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IRandomNumberFactory randomNumberFactory = playerFactory.getRandomNumber();
        when(randomNumberFactoryMock.generateRandomIntegerNumber(1,10)).thenReturn(5);
        int randomInteger = randomNumberFactory.generateRandomIntegerNumber(1,10);
        assertTrue(randomInteger>=1 && randomInteger<=10);
    }
}

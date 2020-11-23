package com.dhl.g05.player;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RandomNumberFactoryTest {

    @Test
    public void generateRandomDoubleNumberTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IRandomNumberFactory randomNumberFactory = new RandomNumberFactory();
        when(randomNumberFactoryMock.generateRandomDoubleNumber(1,10)).thenReturn(5.6);
        double randomDecimal = randomNumberFactory.generateRandomDoubleNumber(1,10);
        assertTrue(randomDecimal>=1 && randomDecimal<=10);
    }

    @Test
    public void generateRandomIntegerNumberTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IRandomNumberFactory randomNumberFactory = new RandomNumberFactory();
        when(randomNumberFactoryMock.generateRandomIntegerNumber(1,10)).thenReturn(5);
        int randomInteger = randomNumberFactory.generateRandomIntegerNumber(1,10);
        assertTrue(randomInteger>=1 && randomInteger<=10);
    }
}
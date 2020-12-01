package com.dhl.g05.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import com.dhl.g05.ApplicationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class RandomNumberFactoryTest {

    private static ModelAbstractFactory modelAbstractFactory;

    @BeforeClass
    public static void init() {
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
    }

    @Test
    public void generateRandomDoubleNumberTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IRandomNumberFactory randomNumberFactory = modelAbstractFactory.createRandomNumber();
        when(randomNumberFactoryMock.generateRandomDoubleNumber(1,10)).thenReturn(5.6);
        double randomDecimal = randomNumberFactory.generateRandomDoubleNumber(1,10);
        assertTrue(randomDecimal>=1 && randomDecimal<=10);
    }

    @Test
    public void generateRandomIntegerNumberTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IRandomNumberFactory randomNumberFactory = modelAbstractFactory.createRandomNumber();
        when(randomNumberFactoryMock.generateRandomIntegerNumber(1,10)).thenReturn(5);
        int randomInteger = randomNumberFactory.generateRandomIntegerNumber(1,10);
        assertTrue(randomInteger>=1 && randomInteger<=10);
    }

}

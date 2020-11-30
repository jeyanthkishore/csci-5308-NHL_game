package com.dhl.g05.simulation.statemachine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.InjuryConstant;

import org.junit.BeforeClass;
import org.junit.Test;

public class InjuryConfigTest {

    private static SimulationAbstractFactory simulationAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
    }

    @Test
    public void getRandomInjuryChanceTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setRandomInjuryChance(0.05);
        assertEquals(injury.getRandomInjuryChance(),0.05,0);
    }

    @Test
    public void setRandomInjuryChanceTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setRandomInjuryChance(0.05);
        assertEquals(injury.getRandomInjuryChance(),0.05,0);
    }

    @Test
    public void getInjuryDaysLowTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysLow(1);
        assertSame(injury.getInjuryDaysLow(),1);
    }

    @Test
    public void setInjuryDaysLowTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysLow(1);
        assertSame(injury.getInjuryDaysLow(),1);
    }

    @Test
    public void getInjuryDaysHighTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysHigh(15);
        assertSame(injury.getInjuryDaysHigh(),15);
    }

    @Test
    public void setInjuryDaysHighTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysHigh(16);
        assertSame(injury.getInjuryDaysHigh(),16);
    }

    @Test
    public void isRandomInjuryChanceValidTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setRandomInjuryChance(1.5);
        assertFalse(injury.isRandomInjuryChanceNotValid(injury.getRandomInjuryChance()));
    }

    @Test
    public void isRandomInjuryChanceNotValidTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setRandomInjuryChance(-5);
        assertTrue(injury.isRandomInjuryChanceNotValid(injury.getRandomInjuryChance()));
    }

    @Test
    public void isInjuryDaysHighValueValidTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysHigh(60);
        injury.setInjuryDaysLow(50);
        assertFalse(injury.isInjuryDaysHighValueNotValid(injury.getInjuryDaysLow(), injury.getInjuryDaysHigh()));
    }

    @Test
    public void isInjuryDaysHighValueNotValidTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysHigh(60);
        injury.setInjuryDaysLow(70);
        assertTrue(injury.isInjuryDaysHighValueNotValid(injury.getInjuryDaysLow(), injury.getInjuryDaysHigh()));
    }

    @Test
    public void randomInjuryChanceValidateTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setRandomInjuryChance(-5);
        assertSame(InjuryConstant.RandomInjuryChanceError, injury.validate());
    }

    @Test
    public void injuryDaysValidateTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setRandomInjuryChance(0.05);
        injury.setInjuryDaysLow(55);
        injury.setInjuryDaysHigh(50);
        assertSame(InjuryConstant.InjuryDaysError, injury.validate());
    }

    @Test
    public void validateTest() {
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        injury.setInjuryDaysLow(10);
        injury.setInjuryDaysHigh(25);
        injury.setRandomInjuryChance(0.1);
        assertSame(InjuryConstant.Success, injury.validate());
    }
}

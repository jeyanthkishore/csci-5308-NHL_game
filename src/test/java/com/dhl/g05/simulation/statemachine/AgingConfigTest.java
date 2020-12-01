package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class AgingConfigTest {

    private static SimulationAbstractFactory simulationAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
    }

    @Test
    public void getAverageRetirementAgeTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setAverageRetirementAge(37);
        assertSame(aging.getAverageRetirementAge(),37);
    }

    @Test
    public void setAverageRetirementAgeTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setAverageRetirementAge(37);
        assertSame(aging.getAverageRetirementAge(),37);
    }

    @Test
    public void getMaximumAgeTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setMaximumAge(50);
        assertSame(aging.getMaximumAge(),50);
    }

    @Test
    public void setMaximumAgeTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setMaximumAge(50);
        assertSame(aging.getMaximumAge(),50);
    }

    @Test
    public void setStatDecayChanceTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setStatDecayChance(0.05);
        assertEquals(aging.getStatDecayChance(),0.05,0);
    }

    @Test
    public void getStatDecayChanceTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setStatDecayChance(0.01);
        assertEquals(aging.getStatDecayChance(),0.01,0);
    }

    @Test
    public void maximumAgeValidateTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setMaximumAge(-1);
        assertSame(AgingConstant.MaximumAgeNotValid,aging.validate());
    }

    @Test
    public void retirementAgeValidateTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setMaximumAge(50);
        aging.setAverageRetirementAge(-1);
        assertSame(AgingConstant.AverageRetirementAgeNotValid,aging.validate());
    }

    @Test
    public void statDecayChanceValidateTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setMaximumAge(50);
        aging.setAverageRetirementAge(35);
        aging.setStatDecayChance(0);
        assertSame(AgingConstant.StatDecayChanceNotValid,aging.validate());
    }

	@Test
	public void validateTest() {
        IAging aging = simulationAbstractFactory.createAgingConfig();
        aging.setMaximumAge(50);
        aging.setAverageRetirementAge(35);
        aging.setStatDecayChance(0.1);
        assertSame(AgingConstant.Success,aging.validate());
	}

}

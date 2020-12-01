package com.dhl.g05.simulation.statemachine;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameStatConfigTest {

    private static SimulationAbstractFactory simulationAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
    }

    @Test
    public void getPenaltyChanceTest() {
        IGameStatConfig gameStatConfig = simulationAbstractFactory.createGameStatConfig();
        gameStatConfig.setPenaltyChance(0.4);
        assertEquals(gameStatConfig.getPenaltyChance(),0.4,0);
    }

    @Test
    public void setPenaltyChanceTest() {
        IGameStatConfig gameStatConfig = simulationAbstractFactory.createGameStatConfig();
        gameStatConfig.setPenaltyChance(0.4);
        assertEquals(gameStatConfig.getPenaltyChance(),0.4,0);
    }

    @Test
    public void penaltyChanceValidateTest() {
        IGameStatConfig gameStatConfig = simulationAbstractFactory.createGameStatConfig();
        gameStatConfig.setPenaltyChance(1.1);
        assertSame(GameStatConfigConstant.PenaltyChanceNotValid, gameStatConfig.validate());
    }

    @Test
    public void ValidateTest() {
        IGameStatConfig gameStatConfig = simulationAbstractFactory.createGameStatConfig();
        gameStatConfig.setPenaltyChance(0.1);
        assertSame(GameStatConfigConstant.Success, gameStatConfig.validate());
    }

}

package com.dhl.g05.model;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.IInjury;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PlayerInjuryTest {

    private static IPlayerInjured playerInjured;
    private static SimulationAbstractFactory simulationAbstractFactory;
    private static ModelAbstractFactory modelAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
    }

    @Test
    public void checkPlayerInjuryTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        playerInjured = modelAbstractFactory.createPlayerInjury();
        IInjury injury = simulationAbstractFactory.createInjuryConfig();
        IPlayer player = modelAbstractFactory.createPlayerModel();
        injury.setInjuryDaysHigh(100);
        injury.setInjuryDaysLow(10);
        injury.setRandomInjuryChance(1.5);
        player.setInjuryStatus(true);
        assertTrue(playerInjured.checkPlayerInjury(player,injury));
        player.setInjuryStatus(false);
        when(randomNumberFactoryMock.generateRandomDoubleNumber(0,1)).thenReturn(0.01);
        assertTrue(playerInjured.checkPlayerInjury(player,injury));
    }
}

package com.dhl.g05.model;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.IAging;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PlayerRetirementTest {

    private static SimulationAbstractFactory simulationAbstractFactory;
    private static ModelAbstractFactory modelAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
    }

    @Test
    public void checkPlayerRetirementTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IPlayerRetired playerRetired = modelAbstractFactory.createPlayerRetirement();
        IPlayer player = modelAbstractFactory.createPlayerModel();
        IAging aging = simulationAbstractFactory.createAgingConfig();
        player.setAge(50);
        aging.setMaximumAge(40);
        assertTrue(playerRetired.checkPlayerRetirement(aging,player));
        player.setAge(30);
        aging.setAverageRetirementAge(35);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(15,0)).thenReturn(7);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(aging.getAverageRetirementAge(),0)).thenReturn(31);
        playerRetired.checkPlayerRetirement(aging,player);
        player.setAge(35);
        aging.setAverageRetirementAge(30);
        aging.setMaximumAge(50);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(50,16)).thenReturn(17);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(aging.getMaximumAge(),aging.getAverageRetirementAge())).thenReturn(35);
        playerRetired.checkPlayerRetirement(aging,player);
    }

    @Test
    public void isFreeAgentsRetiredTest() {
        LeagueMockData mock = new LeagueMockData();
        ILeague league = new LeagueModel(mock);
        IFreeAgent freeAgent = new FreeAgentModel(mock);
        IPlayerRetired playerRetired = modelAbstractFactory.createPlayerRetirement();
        assertTrue(playerRetired.isFreeAgentsRetired(league,freeAgent));
    }

    @Test
    public void isPlayerRetiredTest() {
        LeagueMockData mock = new LeagueMockData();
        ILeague league = new LeagueModel(mock);
        ITeam team = new TeamModel(mock);
        IPlayer player = new PlayerModel(mock);
        IPlayerRetired playerRetired = modelAbstractFactory.createPlayerRetirement();
        assertTrue(playerRetired.isPlayerRetired(league,player,team));
    }
}

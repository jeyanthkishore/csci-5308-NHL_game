package com.dhl.g05.model;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.IAging;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;

public class PlayerRetirementTest {

    private static SimulationAbstractFactory simulationAbstractFactory;
    private static ModelAbstractFactory modelAbstractFactory;
    private static ModelMockAbstractFactory modelMockAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
        modelMockAbstractFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
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
    }

    @Test
    public void isFreeAgentsRetiredTest() {
        LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
        ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
        IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel(leagueMock);
        IPlayerRetired playerRetired = modelAbstractFactory.createPlayerRetirement();
        assertTrue(playerRetired.isFreeAgentsRetired(league,freeAgent));
    }

    @Test
    public void isPlayerRetiredTest() {
        LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
        ILeague league = modelAbstractFactory.createLeagueModel();
        ITeam team = modelAbstractFactory.createTeamModel(leagueMock);
        IPlayer player = modelAbstractFactory.createPlayerModel(leagueMock);
        IPlayerRetired playerRetired = modelAbstractFactory.createPlayerRetirement();
        assertTrue(playerRetired.isPlayerRetired(league,player,team));
    }
}

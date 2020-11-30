package com.dhl.g05.model;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IInjury;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerModelTest {

    private static SimulationAbstractFactory simulationAbstractFactory;
    private static ModelAbstractFactory modelAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
    }

	@Test
	public void constructorTest() {
		IPlayer player = modelAbstractFactory.createPlayerModel();
		assertNull(player.getPlayerName());
		assertNull(player.getPosition());
		assertNull(player.getCaptain());
	}

	@Test
	public void parameterConstructorTest() {
		LeagueMockData playerMock = new LeagueMockData();
		IPlayer player = modelAbstractFactory.createPlayerModel(playerMock.playerOneName, playerMock.positionForward, playerMock.captainOne, playerMock.skating, playerMock.shooting, playerMock.checking, playerMock.saving, playerMock.birthDay, playerMock.birthMonth, playerMock.birthYear);
		assertSame(playerMock.playerOneName, player.getPlayerName());
		assertSame(playerMock.positionForward, player.getPosition());
		assertSame(playerMock.captainOne, player.getCaptain());
		assertEquals(playerMock.skating, player.getSkating(),0);
		assertEquals(playerMock.shooting, player.getShooting(),0);
		assertEquals(playerMock.checking, player.getChecking(),0);
		assertEquals(playerMock.saving, player.getSaving(),0);
		assertEquals(playerMock.birthDay, player.getBirthDay());
		assertEquals(playerMock.birthMonth, player.getBirthMonth());
		assertEquals(playerMock.birthYear, player.getBirthYear());
	}

	@Test
	public void getCaptainTest() {
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setCaptain(true);
		assertTrue(player.getCaptain());
	}

	@Test
	public void setCaptainTest() {
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setCaptain(true);
		assertTrue(player.getCaptain());
	}

	@Test
	public void getInjuredForNumberOfDaysTest() {
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setInjuredForNumberOfDays(30);
		assertSame(player.getInjuredForNumberOfDays(), 30);
	}

	@Test
	public void setInjuredForNumberOfDaysTest() {
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setInjuredForNumberOfDays(30);
		assertSame(player.getInjuredForNumberOfDays(), 30);
	}

	@Test
	public void checkPlayerInjuryTest() {
		IPlayerInjured playerInjured = modelAbstractFactory.createPlayerInjury();
		IPlayerInjury playerInjury = modelAbstractFactory.createInjuredPlayer();
		IPlayer player = modelAbstractFactory.createPlayerModel();
		IInjury injury = simulationAbstractFactory.createInjuryConfig();
		assertFalse(playerInjury.checkPlayerInjury(playerInjured,player, injury));
	}

	@Test
	public void checkPlayerRetirementTest() {
		IPlayerRetirement playerRetirement = modelAbstractFactory.createRetiredPlayer();
		IPlayerRetired playerRetired = modelAbstractFactory.createPlayerRetirement();
		IPlayer player = modelAbstractFactory.createPlayerModel();
		IAging aging = simulationAbstractFactory.createAgingConfig();
		assertTrue(playerRetirement.checkPlayerRetirement(playerRetired, player, aging));
	}

	@Test
	public void validatePlayerTest() {
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setPlayerName("Ronaldo");
		player.setPosition("forward");
		player.setCaptain(true);
		player.setBirthDay(12);
		player.setBirthMonth(11);
		player.setBirthYear(11);
		assertEquals(FreeAgentConstant.Success, player.validate() );
	}
	
}

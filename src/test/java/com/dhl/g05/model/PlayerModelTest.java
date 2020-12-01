package com.dhl.g05.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IInjury;

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
	public void referenceConstructorTest() {
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
	public void setISActiveTest()
	{
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setIsActive(BooleanValue.True.getValue());
		assertTrue(player.getIsActive());
	}
	
	@Test 
	public void setISInActiveTest()
	{
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.setIsActive(BooleanValue.False.getValue());
		assertFalse(player.getIsActive());
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
	@Test
	public void PlayerStatDecayOnBirthdayTest()
	{
		IAging aging = simulationAbstractFactory.createAgingConfig();
		IPlayer player=modelAbstractFactory.createPlayerModel();
		player.setPlayerName("Player1");
		player.setPosition("goalie");
		LocalDate todaysDate= LocalDate.now();
		player.setBirthDay(todaysDate.getDayOfMonth());
		player.setBirthMonth(todaysDate.getMonthValue());
		player.setBirthYear(2000);
		player.setSkating(10);
		player.setShooting(12);
		player.setChecking(15);
		player.setSaving(18);
		aging.setStatDecayChance(1);
		player.decreasePlayerStatOnBirthday(player, aging);
		assertEquals(player.getChecking(), 14, 0);
		assertEquals(player.getSaving(), 17, 0);
		assertEquals(player.getSkating(), 9, 0);
		assertEquals(player.getShooting(), 11, 0);
	}
	
	@Test
	public void PlayerStatNotDecayOnBirthdayTest()
	{
		IAging aging = simulationAbstractFactory.createAgingConfig();
		IPlayer player=modelAbstractFactory.createPlayerModel();
		player.setPlayerName("Player1");
		player.setPosition("goalie");
		LocalDate todaysDate= LocalDate.now();
		player.setBirthDay(todaysDate.getDayOfMonth());
		player.setBirthMonth(todaysDate.getMonthValue());
		player.setBirthYear(2000);
		player.setSkating(10);
		player.setShooting(12);
		player.setChecking(15);
		player.setSaving(18);
		aging.setStatDecayChance(0);
		player.decreasePlayerStatOnBirthday(player, aging);
		assertEquals(player.getChecking(), 15, 0);
		assertEquals(player.getSaving(), 18, 0);
		assertEquals(player.getSkating(), 10, 0);
		assertEquals(player.getShooting(), 12, 0);
	}
	
	@Test
	public void convertFreeAgentToPlayerTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setAge(20);
		freeAgent.setBirthDay(19);
		freeAgent.setBirthMonth(2);
		freeAgent.setBirthYear(2000);
		freeAgent.setChecking(11);
		freeAgent.setSaving(12);
		freeAgent.setShooting(12);
		freeAgent.setSkating(15);
		freeAgent.setPosition("forward");
		freeAgent.setPlayerName("Rahul");
		
		IPlayer player = modelAbstractFactory.createPlayerModel();
		player.convertFreeAgentToPlayer(freeAgent, true);
		assertEquals("Rahul",player.getPlayerName());
		assertEquals(15,player.getSkating(),0);
		assertEquals(2000,player.getBirthYear());
	}
	
}
	

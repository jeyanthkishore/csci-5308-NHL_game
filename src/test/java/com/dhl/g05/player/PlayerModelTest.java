package com.dhl.g05.player;
import com.dhl.g05.gameplayconfig.*;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.freeagent.FreeAgentConstant;
import com.dhl.g05.mockdata.JsonMockDataDb;

import static org.junit.Assert.*;


public class PlayerModelTest {

	private static AbstractPlayerFactory playerFactory;
	private static AbstractGamePlayConfigFactory gamePlayConfigFactory;

	@BeforeClass
	public static void setup() {
		AbstractPlayerFactory.setFactory(new PlayerFactory());
		AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
		playerFactory = AbstractPlayerFactory.getFactory();
		gamePlayConfigFactory = AbstractGamePlayConfigFactory.getFactory();
	}

	@Test
	public void constructorTest() {
		IPlayer player = playerFactory.getPlayer();
		assertNull(player.getPlayerName());
		assertNull(player.getPosition());
		assertNull(player.getCaptain());
	}

	@Test
	public void parameterConstructorTest() {
		JsonMockDataDb playerMock = new JsonMockDataDb();
		IPlayer player = playerFactory.getPlayer(playerMock.playerOneName, playerMock.positionForward, playerMock.captainOne, playerMock.skating, playerMock.shooting, playerMock.checking, playerMock.saving, playerMock.birthDay, playerMock.birthMonth, playerMock.birthYear);
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
		IPlayer player = playerFactory.getPlayer();
		player.setCaptain(true);
		assertTrue(player.getCaptain());
	}

	@Test
	public void setCaptainTest() {
		IPlayer player = playerFactory.getPlayer();
		player.setCaptain(true);
		assertTrue(player.getCaptain());
	}

	@Test
	public void getInjuredForNumberOfDaysTest() {
		IPlayer player = playerFactory.getPlayer();
		player.setInjuredForNumberOfDays(30);
		assertSame(player.getInjuredForNumberOfDays(), 30);
	}

	@Test
	public void setInjuredForNumberOfDaysTest() {
		IPlayer player = playerFactory.getPlayer();
		player.setInjuredForNumberOfDays(30);
		assertSame(player.getInjuredForNumberOfDays(), 30);
	}

	@Test
	public void checkPlayerInjuryTest() {
		IPlayerInjured playerInjured = playerFactory.getPlayerInjury();
		IPlayerInjury playerInjury = playerFactory.getInjuredPlayer();
		IPlayer player = playerFactory.getPlayer();
		IInjury injury = gamePlayConfigFactory.getInjury();
		assertFalse(playerInjury.checkPlayerInjury(playerInjured,player, injury));
	}

	@Test
	public void checkPlayerRetirementTest() {
		IPlayerRetirement playerRetirement = playerFactory.getRetiredPlayer();
		IPlayerRetired playerRetired = playerFactory.getPlayerRetirement();
		IPlayer player = playerFactory.getPlayer();
		IAging aging = gamePlayConfigFactory.getAging();
		assertTrue(playerRetirement.checkPlayerRetirement(playerRetired, player, aging));
	}

	@Test
	public void validatePlayerTest() {
		IPlayer player = playerFactory.getPlayer();
		player.setPlayerName("Ronaldo");
		player.setPosition("forward");
		player.setCaptain(true);
		player.setBirthDay(12);
		player.setBirthMonth(11);
		player.setBirthYear(11);
		assertEquals(FreeAgentConstant.Success, player.validate() );
	}
}
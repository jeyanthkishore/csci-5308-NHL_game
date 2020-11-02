package com.dhl.g05.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.freeagent.FreeAgentConstant;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class PlayerModelTest {

	@Test
	public void constructorTest() {
		PlayerModel object = new PlayerModel();
		assertNull(object.getPlayerName());
		assertNull(object.getPosition());
		assertNull(object.getCaptain());
	}

	@Test
	public void setPlayerNameTest() {
		PlayerModel object = new PlayerModel();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}

	@Test
	public void getPlayerNameTest() {
		PlayerModel object = new PlayerModel();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}

	@Test
	public void setPositionTest() {
		PlayerModel object = new PlayerModel();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}

	@Test
	public void getPositionTest() {
		PlayerModel object = new PlayerModel();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}

	@Test
	public void setCaptainTest() {
		PlayerModel object = new PlayerModel();
		object.setCaptain(true);
		assertTrue(object.getCaptain());
	}

	@Test
	public void getCaptainTest() {
		PlayerModel object = new PlayerModel();
		object.setCaptain(true);
		assertTrue(object.getCaptain());
	}

	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.Success,player.validate());
	}

	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.Success,player.validate());
	}

	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,player.validate());
	}

	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,player.validate());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,player.validate());
	}

	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,player.validate());
	}

	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.Success,player.validate());
	}

	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,player.validate());
	}

	@Test
	public void captainNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNull();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.CaptainNull,player.validate());
	}
	@Test
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.Success,player.validate());
		mock.setPlayerPositionEmpty();
		player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,player.validate());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,player.validate());
		mock = new JsonMockDataDb();
		mock.setCaptainNull();
		player = new PlayerModel(mock);
		assertSame(FreeAgentConstant.CaptainNull,player.validate());
	}

	@Test
	public void savePlayerObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerPersistenceMock data = new PlayerPersistenceMock();
		PlayerModel valid = new PlayerModel(mock);
		assertEquals(1,valid.savePlayerObject(1,data));
	}

	@Test
	public void loadPlayerObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerPersistenceMock data = new PlayerPersistenceMock();
		PlayerModel valid = new PlayerModel(mock);
		assertEquals(1,valid.loadPlayerObject(1,data));
	}

	@Test
	public void isInjured() {
		IPlayerProgress playerProgress = new PlayerProgress(new RandomGeneratorFactory());
		IPlayerInjury playerInjury = new PlayerModel();
		PlayerModel player = new PlayerModel();
		IInjury injury = new Injury();
		assertTrue(playerInjury.isInjured(playerProgress, player,injury));
	}

	@Test
	public void calculatePlayerAgeByDays() {
		PlayerModel player = new PlayerModel();
		player.setAge(25);
		player.calculatePlayerAgeByDays(30);
		assertEquals(25,player.getAge());
		assertEquals(30,player.getElapsedDaysSinceLastBDay());

		player = new PlayerModel();
		player.setAge(30);
		player.calculatePlayerAgeByDays(365);
		assertEquals(31,player.getAge());
		assertEquals(0,player.getElapsedDaysSinceLastBDay());

		player = new PlayerModel();
		player.setAge(25);
		player.calculatePlayerAgeByDays(520);
		assertEquals(26,player.getAge());
		assertEquals(155,player.getElapsedDaysSinceLastBDay());

	}
}
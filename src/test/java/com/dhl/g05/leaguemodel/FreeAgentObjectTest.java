package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FreeAgentObjectTest {
	
	@Test
	public void constructorTest() {
		FreeAgentObject object = new FreeAgentObject();
		assertNull(object.getPlayerName());
		assertNull(object.getPosition());
		assertFalse(object.getHasInjured());
	}

	@Test
	public void parameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CoachObject object = new CoachObject(data.headCoachName, data.coachSkating, data.coachShooting, data.coachChecking, data.coachSaving);
		assertSame(data.headCoachName,object.getName());
		assertEquals(data.coachSkating, object.getSkating(),0);
		assertEquals(data.coachShooting, object.getShooting(),0);
		assertEquals(data.coachChecking, object.getChecking(),0);
		assertEquals(data.coachSaving, object.getSaving(),0);
	}

	@Test
	public void coachObjectReferenceConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CoachObject object = new CoachObject(data.headCoachName, data.coachSkating, data.coachShooting, data.coachChecking, data.coachSaving);
		assertSame(data.headCoachName,object.getName());
		assertEquals(data.coachSkating, object.getSkating(),0);
		assertEquals(data.coachShooting, object.getShooting(),0);
		assertEquals(data.coachChecking, object.getChecking(),0);
		assertEquals(data.coachSaving, object.getSaving(),0);
	}


	@Test
	public void setPlayerNameTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}

	@Test
	public void getPlayerNameTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}


	@Test
	public void setAgeTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setAge(10.0);
		assertEquals(object.getAge(),10.0,0);
	}

	@Test
	public void getAgeTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setAge(10.0);
		assertEquals(object.getAge(),10.0,0);
	}

	@Test
	public void setSkatingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setSkating(10.0);
		assertEquals(object.getSkating(),10.0,0);
	}

	@Test
	public void getSkatingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setSkating(10.0);
		assertEquals(object.getSkating(),10.0,0);
	}

	@Test
	public void setShootingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setShooting(10.0);
		assertEquals(object.getShooting(),10.0,0);
	}

	@Test
	public void getShootingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setShooting(10.0);
		assertEquals(object.getShooting(),10.0,0);
	}

	@Test
	public void setCheckingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setChecking(10.0);
		assertEquals(object.getChecking(),10.0,0);
	}

	@Test
	public void getCheckingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setChecking(10.0);
		assertEquals(object.getChecking(),10.0,0);
	}

	@Test
	public void setSavingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setSaving(10.0);
		assertEquals(object.getSaving(),10.0,0);
	}

	@Test
	public void getSavingTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setSaving(10.0);
		assertEquals(object.getSaving(),10.0,0);
	}

	@Test
	public void setPlayerStrengthTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setPlayerStrength(15);
		assertEquals(object.getPlayerStrength(),15,0);
	}

	@Test
	public void getPlayerStrengthTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setPlayerStrength(15);
		assertEquals(object.getPlayerStrength(),15,0);
	}

	@Test
	public void setHasInjuredTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setHasInjured(true);
		assertSame(object.getHasInjured(),true);
	}

	@Test
	public void getHasInjuredTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setHasInjured(true);
		assertSame(object.getHasInjured(),true);
	}

	@Test
	public void setPositionTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}

	@Test
	public void getPositionTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}

	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertTrue(validate.isPlayerPositionValid());
	}
	
	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertFalse(validate.isPlayerPositionValid());
	}

	@Test
	public void isPlayerAgeValidTest() {
		FreeAgentObject validate = new FreeAgentObject();
		validate.setAge(25);
		assertEquals(validate.isPlayerAgeValid(),true);
	}

	@Test
	public void validateStatsTest() {
		FreeAgentObject validate = new FreeAgentObject();
		validate.setSkating(12);
		assertEquals(validate.validateStat(12),true);
	}

	@Test
	public void validateStatsInverseTest() {
		FreeAgentObject validate = new FreeAgentObject();
		validate.setSkating(-1);
		assertEquals(validate.validateStat(-1),false);
	}

	@Test
	public void isPlayerStatValidTest() {
		FreeAgentObject validate = new FreeAgentObject();
		validate.setSkating(12);
		validate.setShooting(10);
		validate.setChecking(5);
		validate.setSaving(15);
		assertEquals(validate.isPlayerStatValid(),true);
	}

	@Test
	public void calculateForwardPlayerStrengthTest(){
		JsonMockDataDb data = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(data.playerOneName,data.positionForward,data.age, data.skating, data.shooting, data.checking, data.saving);
		assertEquals(validate.calculatePlayerStrength(),data.calculatePlayerStrength(data.positionForward),0);
	}

	@Test
	public void calculateDefensePlayerStrengthTest(){
		JsonMockDataDb data = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(data.playerOneName,data.positionDefense,data.age, data.skating, data.shooting, data.checking, data.saving);
		assertEquals(validate.calculatePlayerStrength(),data.calculatePlayerStrength(data.positionDefense),0);
	}

	@Test
	public void calculateGoaliePlayerStrengthTest(){
		JsonMockDataDb data = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(data.playerOneName,data.positionGoalie,data.age, data.skating, data.shooting, data.checking, data.saving);
		assertEquals(validate.calculatePlayerStrength(),data.calculatePlayerStrength(data.positionGoalie),0);
	}

	@Test
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertSame(ValidateEnumModel.Success,validate.validate());
		mock.setPlayerPositionEmpty();
		validate = new FreeAgentObject(mock);
		assertSame(ValidateEnumModel.PlayerValueEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		validate = new FreeAgentObject(mock);
		assertSame(ValidateEnumModel.PlayerPositionWrong,validate.validate());
		mock = new JsonMockDataDb();
		validate = new FreeAgentObject(mock);
		validate.setAge(-1);
		assertSame(ValidateEnumModel.PlayerAgeInvalid,validate.validate());
		mock = new JsonMockDataDb();
		validate = new FreeAgentObject(mock);
		validate.setSkating(-1);
		assertSame(ValidateEnumModel.PlayerStateInvalid,validate.validate());
	}


}

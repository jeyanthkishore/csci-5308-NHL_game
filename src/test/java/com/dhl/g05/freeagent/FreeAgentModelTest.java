package com.dhl.g05.freeagent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.MockData.JsonMockDataDb;
import com.dhl.g05.coach.CoachModel;

public class FreeAgentModelTest {

	@Test
	public void constructorTest() {
		FreeAgentModel object = new FreeAgentModel();
		assertNull(object.getPlayerName());
		assertNull(object.getPosition());
		assertFalse(object.getInjuredStatus());
	}

	@Test
	public void parameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CoachModel object = new CoachModel(data.headCoachName, data.coachSkating, data.coachShooting, data.coachChecking, data.coachSaving);
		assertSame(data.headCoachName,object.getName());
		assertEquals(data.coachSkating, object.getSkating(),0);
		assertEquals(data.coachShooting, object.getShooting(),0);
		assertEquals(data.coachChecking, object.getChecking(),0);
		assertEquals(data.coachSaving, object.getSaving(),0);
	}

	@Test
	public void coachObjectReferenceConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CoachModel object = new CoachModel(data.headCoachName, data.coachSkating, data.coachShooting, data.coachChecking, data.coachSaving);
		assertSame(data.headCoachName,object.getName());
		assertEquals(data.coachSkating, object.getSkating(),0);
		assertEquals(data.coachShooting, object.getShooting(),0);
		assertEquals(data.coachChecking, object.getChecking(),0);
		assertEquals(data.coachSaving, object.getSaving(),0);
	}


	@Test
	public void setPlayerNameTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}

	@Test
	public void getPlayerNameTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}


	@Test
	public void setAgeTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setAge(10);
		assertSame(object.getAge(),10);
	}

	@Test
	public void getAgeTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setAge(10);
		assertSame(object.getAge(),10);
	}

	@Test
	public void setSkatingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setSkating(10.0);
		assertEquals(object.getSkating(),10.0,0);
	}

	@Test
	public void getSkatingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setSkating(10.0);
		assertEquals(object.getSkating(),10.0,0);
	}

	@Test
	public void setShootingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setShooting(10.0);
		assertEquals(object.getShooting(),10.0,0);
	}

	@Test
	public void getShootingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setShooting(10.0);
		assertEquals(object.getShooting(),10.0,0);
	}

	@Test
	public void setCheckingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setChecking(10.0);
		assertEquals(object.getChecking(),10.0,0);
	}

	@Test
	public void getCheckingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setChecking(10.0);
		assertEquals(object.getChecking(),10.0,0);
	}

	@Test
	public void setSavingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setSaving(10.0);
		assertEquals(object.getSaving(),10.0,0);
	}

	@Test
	public void getSavingTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setSaving(10.0);
		assertEquals(object.getSaving(),10.0,0);
	}

	@Test
	public void setPlayerStrengthTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setPlayerStrength(15);
		assertEquals(object.getPlayerStrength(),15,0);
	}

	@Test
	public void getPlayerStrengthTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setPlayerStrength(15);
		assertEquals(object.getPlayerStrength(),15,0);
	}

	@Test
	public void setHasInjuredTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setInjuredStatus(true);
		assertTrue(object.getInjuredStatus());
	}

	@Test
	public void getHasInjuredTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setInjuredStatus(true);
		assertTrue(object.getInjuredStatus());
	}

	@Test
	public void setIsRetiredTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setRetiredStatus(true);
		assertTrue(object.getRetiredStatus());
	}

	@Test
	public void getIsRetiredTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setRetiredStatus(true);
		assertTrue(object.getRetiredStatus());
	}

	@Test
	public void setPositionTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}

	@Test
	public void getPositionTest() {
		FreeAgentModel object = new FreeAgentModel();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}

	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,validate.validate());
	}

	@Test
	public void isPlayerAgeValidTest() {
		FreeAgentModel validate = new FreeAgentModel();
		validate.setAge(25);
		assertEquals(validate.isPlayerAgeNotValid(),false);
	}

	@Test
	public void validateStatsTest() {
		FreeAgentModel validate = new FreeAgentModel();
		validate.setSkating(12);
		assertEquals(validate.validateStat(12),true);
	}

	@Test
	public void validateStatsInverseTest() {
		FreeAgentModel validate = new FreeAgentModel();
		validate.setSkating(-1);
		assertEquals(validate.validateStat(-1),false);
	}

	@Test
	public void isPlayerStatValidTest() {
		FreeAgentModel validate = new FreeAgentModel();
		validate.setSkating(12);
		validate.setShooting(10);
		validate.setChecking(5);
		validate.setSaving(15);
		assertEquals(validate.isPlayerStatNotValid(),false);
	}

	@Test
	public void calculateForwardPlayerStrengthTest(){
		JsonMockDataDb data = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(data.playerOneName,data.positionForward,data.age, data.skating, data.shooting, data.checking, data.saving);
		assertEquals(validate.calculatePlayerStrength(),data.calculatePlayerStrength(data.positionForward),0);
	}

	@Test
	public void calculateDefensePlayerStrengthTest(){
		JsonMockDataDb data = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(data.playerOneName,data.positionDefense,data.age, data.skating, data.shooting, data.checking, data.saving);
		assertEquals(validate.calculatePlayerStrength(),data.calculatePlayerStrength(data.positionDefense),0);
	}

	@Test
	public void calculateGoaliePlayerStrengthTest(){
		JsonMockDataDb data = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(data.playerOneName,data.positionGoalie,data.age, data.skating, data.shooting, data.checking, data.saving);
		assertEquals(validate.calculatePlayerStrength(),data.calculatePlayerStrength(data.positionGoalie),0);
	}

	@Test
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
		mock.setPlayerPositionEmpty();
		validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,validate.validate());
		mock = new JsonMockDataDb();
		validate = new FreeAgentModel(mock);
		validate.setAge(-1);
		assertSame(FreeAgentConstant.PlayerAgeInvalid,validate.validate());
		mock = new JsonMockDataDb();
		validate = new FreeAgentModel(mock);
		validate.setSkating(-1);
		assertSame(FreeAgentConstant.PlayerStateInvalid,validate.validate());
	}

}

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
	public void getResultTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setResult("success");
		assertEquals("success",object.getResult());
	}
	@Test
	public void setResultTest() {
		FreeAgentObject object = new FreeAgentObject();
		object.setResult("success");
		assertEquals("success",object.getResult());
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
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentObject validate = new FreeAgentObject(mock);
		assertEquals("success",validate.validate());
		mock.setPlayerPositionEmpty();
		validate = new FreeAgentObject(mock);
		assertEquals("Player Should Not have Empty Value",validate.validate());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		validate = new FreeAgentObject(mock);
		assertEquals("Player Position Is Wrong",validate.validate());
	}


}

package com.dhl.g05.leaguemodel.coach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;

public class CoachModelTest {

	@Test
	public void constructorTest() {
		CoachModel object = new CoachModel();
		assertNull(object.getName());
	}

	@Test
	public void setNameTest() {
		CoachModel object = new CoachModel();
		object.setName("Smith");
		assertSame(object.getName(),"Smith");
	}

	@Test
	public void getNameTest() {
		CoachModel object = new CoachModel();
		object.setName("Smith");
		assertSame(object.getName(),"Smith");
	}

	@Test
	public void setSkatingTest() {
		CoachModel object = new CoachModel();
		object.setSkating(0.5);
		assertEquals(object.getSkating(),0.5,0);
	}

	@Test
	public void getSkatingTest() {
		CoachModel object = new CoachModel();
		object.setSkating(0.5);
		assertEquals(object.getSkating(),0.5,0);
	}

	@Test
	public void setShootingTest() {
		CoachModel object = new CoachModel();
		object.setShooting(0.5);
		assertEquals(object.getShooting(),0.5,0);
	}

	@Test
	public void getShootingTest() {
		CoachModel object = new CoachModel();
		object.setShooting(0.5);
		assertEquals(object.getShooting(),0.5,0);
	}

	@Test
	public void setCheckingTest() {
		CoachModel object = new CoachModel();
		object.setChecking(0.5);
		assertEquals(object.getChecking(),0.5,0);
	}

	@Test
	public void getCheckingTest() {
		CoachModel object = new CoachModel();
		object.setChecking(0.5);
		assertEquals(object.getChecking(),0.5,0);
	}

	@Test
	public void setSavingTest() {
		CoachModel object = new CoachModel();
		object.setSaving(0.5);
		assertEquals(object.getSaving(),0.5,0);
	}

	@Test
	public void getSavingTest() {
		CoachModel object = new CoachModel();
		object.setSaving(0.5);
		assertEquals(object.getSaving(),0.5,0);
	}

	@Test
	public void isCoachNameNullTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		data.setCoachNameNull();
		CoachModel object = new CoachModel(data);
		assertTrue(object.isCoachNameNull());
	}

	@Test
	public void isCoachNameEmptyTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		data.setCoachNameEmpty();
		CoachModel object = new CoachModel(data);
		assertTrue(object.isCoachNameEmpty());
	}

	@Test
	public void validateStatsTest() {
		CoachModel validate = new CoachModel();
		validate.setSkating(0.7);
		assertEquals(validate.validateStat(0.7),true);
	}

	@Test
	public void validateStatsInverseTest() {
		FreeAgentModel validate = new FreeAgentModel();
		validate.setSkating(-1);
		assertEquals(validate.validateStat(-1),false);
	}

	@Test
	public void isCoachStatValidTest() {
		CoachModel validate = new CoachModel();
		validate.setSkating(0.5);
		validate.setShooting(0.3);
		validate.setChecking(0.5);
		validate.setSaving(0.6);
		assertEquals(validate.isCoachStatValid(),true);
	}

	@Test
	public void isCoachStatInValidTest() {
		CoachModel validate = new CoachModel();
		validate.setSkating(1.5);
		validate.setShooting(0.3);
		validate.setChecking(0.5);
		validate.setSaving(0.6);
		assertEquals(validate.isCoachStatValid(),false);
	}

	@Test
	public void validateTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachNameEmpty();
		CoachModel validate = new CoachModel(mock);
		assertEquals(CoachConstant.CoachNameEmpty,validate.validate());
		mock = new JsonMockDataDb();
		validate = new CoachModel(mock);
		validate.setSkating(-1);
		assertEquals(CoachConstant.InvalidStateOfCoach,validate.validate());
	}

}


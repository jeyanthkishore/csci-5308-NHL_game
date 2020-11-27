package com.dhl.g05.coach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.dhl.g05.mockdata.JsonMockDataDb;

public class CoachModelTest {

	@Test
	public void constructorTest() {
		ICoach coach = new CoachModel();
		assertNull(coach.getName());
	}

	@Test
	public void getNameTest() {
		ICoach coach = new CoachModel();
		coach.setName("Smith");
		assertSame(coach.getName(),"Smith");
	}

	@Test
	public void setNameTest() {
		ICoach coach = new CoachModel();
		coach.setName("Smith");
		assertSame(coach.getName(),"Smith");
	}

	@Test
	public void getSkatingTest() {
		ICoach coach = new CoachModel();
		coach.setSkating(0.5);
		assertEquals(coach.getSkating(),0.5,0);
	}

	@Test
	public void setSkatingTest() {
		ICoach coach = new CoachModel();
		coach.setSkating(0.5);
		assertEquals(coach.getSkating(),0.5,0);
	}

	@Test
	public void getShootingTest() {
		ICoach coach = new CoachModel();
		coach.setShooting(0.5);
		assertEquals(coach.getShooting(),0.5,0);
	}

	@Test
	public void setShootingTest() {
		ICoach coach = new CoachModel();
		coach.setShooting(0.5);
		assertEquals(coach.getShooting(),0.5,0);
	}

	@Test
	public void getCheckingTest() {
		ICoach coach = new CoachModel();
		coach.setChecking(0.5);
		assertEquals(coach.getChecking(),0.5,0);
	}

	@Test
	public void setCheckingTest() {
		ICoach coach = new CoachModel();
		coach.setChecking(0.5);
		assertEquals(coach.getChecking(),0.5,0);
	}

	@Test
	public void getSavingTest() {
		ICoach coach = new CoachModel();
		coach.setSaving(0.5);
		assertEquals(coach.getSaving(),0.5,0);
	}

	@Test
	public void setSavingTest() {
		ICoach coach = new CoachModel();
		coach.setSaving(0.5);
		assertEquals(coach.getSaving(),0.5,0);
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

	@Test
	public void isCoachNameNullTest() {
		JsonMockDataDb coachMock = new JsonMockDataDb();
		coachMock.setCoachNameNull();
		ICoach coach = new CoachModel(coachMock);
		assertTrue(coach.isCoachNameNull());
	}

	@Test
	public void isCoachNameEmptyTest() {
		JsonMockDataDb coachMock = new JsonMockDataDb();
		coachMock.setCoachNameEmpty();
		ICoach coach = new CoachModel(coachMock);
		assertTrue(coach.isCoachNameEmpty());
	}

	@Test
	public void isCoachStatNotValidTest() {
		ICoach coach = new CoachModel();
		coach.setSkating(0.5);
		coach.setShooting(0.3);
		coach.setChecking(0.5);
		coach.setSaving(0.6);
		assertEquals(coach.isCoachStatNotValid(),false);
	}

	@Test
	public void isCoachStatValidTest() {
		ICoach coach = new CoachModel();
		coach.setSkating(1.5);
		coach.setShooting(0.3);
		coach.setChecking(0.5);
		coach.setSaving(0.6);
		assertEquals(coach.isCoachStatNotValid(),true);
	}


}


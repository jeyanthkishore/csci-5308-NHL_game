package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.dhl.g05.model.CoachConstant;
import com.dhl.g05.model.CoachModel;
import com.dhl.g05.model.ICoach;

public class CoachModelTest {

	@Test
	public void constructorTest() {
		ICoach coach = new CoachModel();
		assertNull(coach.getName());
	}

	@Test
	public void parameterConstructorTest() {
		LeagueMockData coachMock = new LeagueMockData();
		ICoach coach = new CoachModel(coachMock.headCoachName, coachMock.coachSkating, coachMock.coachShooting, coachMock.coachChecking, coachMock.coachSaving);
		assertSame(coachMock.headCoachName, coach.getName());
		assertEquals(coachMock.coachSkating, coach.getSkating(),0);
		assertEquals(coachMock.coachShooting, coach.getShooting(),0);
		assertEquals(coachMock.coachChecking, coach.getChecking(),0);
		assertEquals(coachMock.coachSaving, coach.getSaving(),0);
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
	public void coachNameValidateTest() {
		ICoach coach = new CoachModel();
		assertEquals(CoachConstant.CoachNameEmpty, coach.validate());
	}

	@Test
	public void coachStatValidateTest() {
		ICoach coach = new CoachModel();
		coach.setName("Alex");
		coach.setChecking(-1);
		assertEquals(CoachConstant.InvalidStateOfCoach, coach.validate());
	}

	@Test
	public void validateTest() {
		ICoach coach = new CoachModel();
		coach.setName("Alex");
		coach.setSkating(0.1);
		coach.setShooting(0.1);
		coach.setSaving(0.1);
		coach.setChecking(0.1);
		assertEquals(CoachConstant.Success, coach.validate());
	}

	@Test
	public void isCoachNameNullTest() {
		LeagueMockData coachMock = new LeagueMockData();
		coachMock.setCoachNameNull();
		ICoach coach = new CoachModel(coachMock);
		assertTrue(coach.isCoachNameNull());
	}

	@Test
	public void isCoachNameEmptyTest() {
		LeagueMockData coachMock = new LeagueMockData();
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

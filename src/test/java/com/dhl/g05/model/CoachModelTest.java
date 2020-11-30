package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.dhl.g05.ApplicationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoachModelTest {

	private static ModelAbstractFactory modelAbstractFactory;

	@BeforeClass
	public static void init() {
		modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	}

	@Test
	public void constructorTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		assertNull(coach.getName());
	}

	@Test
	public void parameterConstructorTest() {
		ICoach coach = modelAbstractFactory.createCoachModel("Xavier", 0.1, 0.2, 0.3, 0.4);
		assertSame("Xavier", coach.getName());
		assertEquals(0.1, coach.getSkating(),0);
		assertEquals(0.2, coach.getShooting(),0);
		assertEquals(0.3, coach.getChecking(),0);
		assertEquals(0.4, coach.getSaving(),0);
	}

	@Test
	public void getNameTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setName("Smith");
		assertSame(coach.getName(),"Smith");
	}

	@Test
	public void setNameTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setName("Smith");
		assertSame(coach.getName(),"Smith");
	}

	@Test
	public void getSkatingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setSkating(0.5);
		assertEquals(coach.getSkating(),0.5,0);
	}

	@Test
	public void setSkatingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setSkating(0.5);
		assertEquals(coach.getSkating(),0.5,0);
	}

	@Test
	public void getShootingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setShooting(0.5);
		assertEquals(coach.getShooting(),0.5,0);
	}

	@Test
	public void setShootingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setShooting(0.5);
		assertEquals(coach.getShooting(),0.5,0);
	}

	@Test
	public void getCheckingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setChecking(0.5);
		assertEquals(coach.getChecking(),0.5,0);
	}

	@Test
	public void setCheckingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setChecking(0.5);
		assertEquals(coach.getChecking(),0.5,0);
	}

	@Test
	public void getSavingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setSaving(0.5);
		assertEquals(coach.getSaving(),0.5,0);
	}

	@Test
	public void setSavingTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setSaving(0.5);
		assertEquals(coach.getSaving(),0.5,0);
	}

	@Test
	public void coachNameValidateTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		assertEquals(CoachConstant.CoachNameEmpty, coach.validate());
	}

	@Test
	public void coachStatValidateTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setName("Alex");
		coach.setChecking(-1);
		assertEquals(CoachConstant.InvalidStateOfCoach, coach.validate());
	}

	@Test
	public void validateTest() {
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setName("Alex");
		coach.setSkating(0.1);
		coach.setShooting(0.1);
		coach.setSaving(0.1);
		coach.setChecking(0.1);
		assertEquals(CoachConstant.Success, coach.validate());
	}
}


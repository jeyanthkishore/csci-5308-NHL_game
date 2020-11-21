package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class AgingTest {

    @Test
    public void parameterConstructorTest1() {
        JsonMockDataDb data = new JsonMockDataDb();
        Aging object = new Aging(data.averageRetirementAge, data.maximumAge, data.statDecayChance);
        assertSame(data.averageRetirementAge,object.getAverageRetirementAge());
        assertSame(data.maximumAge, object.getMaximumAge());
    }
    @Test
    public void parameterConstructorTest2() {
        JsonMockDataDb data = new JsonMockDataDb();
        Aging object = new Aging(data.averageRetirementAge, data.maximumAge, data.statDecayChance);
        assertEquals(data.statDecayChance, object.getStatDecayChance(),0); 
    }

    @Test
    public void getAverageRetirementAgeTest() {
        Aging object = new Aging();
        object.setAverageRetirementAge(37);
        assertSame(object.getAverageRetirementAge(),37);
    }

    @Test
    public void setAverageRetirementAgeTest() {
        Aging object = new Aging();
        object.setAverageRetirementAge(37);
        assertSame(object.getAverageRetirementAge(),37);
    }

    @Test
    public void getMaximumAgeTest() {
        Aging object = new Aging();
        object.setMaximumAge(50);
        assertSame(object.getMaximumAge(),50);
    }

    @Test
    public void setMaximumAgeTest() {
        Aging object = new Aging();
        object.setMaximumAge(50);
        assertSame(object.getMaximumAge(),50);
    }
    @Test
    public void setStatDecayChanceTest() {
        Aging object = new Aging();
        object.setStatDecayChance(0.05);
        assertEquals(object.getStatDecayChance(),0.05,0);
    }
    @Test
    public void getStatDecayChanceTest() {
        Aging object = new Aging();
        object.setStatDecayChance(0.01);
        assertEquals(object.getStatDecayChance(),0.01,0);
    }
    @Test
    public void isStatDecayChanceNotValidTest1() {
        Aging object = new Aging();
        object.setStatDecayChance(0.05);
        assertFalse(object.isStatDecayChanceNotValid(object.getStatDecayChance()));
    }
    @Test
    public void isStatDecayChanceNotValidTest2() {
        Aging object = new Aging();
        object.setStatDecayChance(-0.05);
        assertTrue(object.isStatDecayChanceNotValid(object.getStatDecayChance()));
    }
    @Test
    public void isStatDecayChanceNotValidTest3() {
        Aging object = new Aging();
        object.setStatDecayChance(3.07);
        assertTrue(object.isStatDecayChanceNotValid(object.getStatDecayChance()));
    }
    
    @Test
    public void isMaximumAgeNotValidTest() {
        Aging object= new Aging();
        object.setMaximumAge(12);
        assertFalse(object.isMaximumAgeNotValid(object.getMaximumAge()));
        object.setMaximumAge(-1);
        assertTrue(object.isMaximumAgeNotValid(object.getMaximumAge()));
    }

    @Test
    public void isAverageRetirementAgeNotValidTest() {
        Aging object= new Aging();
        object.setAverageRetirementAge(45);
        assertFalse(object.isAverageRetirementAgeNotValid(object.getAverageRetirementAge()));
        object.setAverageRetirementAge(-1);
        assertTrue(object.isMaximumAgeNotValid(object.getAverageRetirementAge()));
    }

    @Test
    public void validateTest() {
        Aging object = new Aging();
        object.setMaximumAge(-1);
        assertSame(AgingConstant.MaximumAgeNotValid,object.validate());

        object = new Aging();
        object.setMaximumAge(10);
        object.setAverageRetirementAge(-1);
        assertSame(AgingConstant.AverageRetirementAgeNotValid,object.validate());

        object = new Aging();
        object.setMaximumAge(50);
        object.setAverageRetirementAge(35);
        assertSame(AgingConstant.Success,object.validate());
    }

	@Test
	public void validateTest2() {
		Aging object = new Aging(50, 35, 0.05);
		assertSame(AgingConstant.Success, object.validate());
	}

	@Test
	public void validateTest3() {
		Aging object = new Aging(40, 32, -5.66);
		assertSame(AgingConstant.StatDecayChanceNotValid, object.validate());
	}

}

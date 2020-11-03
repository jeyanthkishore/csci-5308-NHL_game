package com.dhl.g05.gameplayconfig;

import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

import static org.junit.Assert.*;

public class InjuryTest {

    @Test
    public void parameterConstructorTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        Injury object = new Injury(data.randomInjuryChance,data.injuryDaysLow,data.injuryDaysHigh);
        assertEquals(data.randomInjuryChance,object.getRandomInjuryChance(),0);
        assertSame(data.injuryDaysLow,object.getInjuryDaysLow());
    }

    @Test
    public void getRandomInjuryChanceTest() {
        Injury object = new Injury();
        object.setRandomInjuryChance(0.05);
        assertEquals(object.getRandomInjuryChance(),0.05,0);
    }

    @Test
    public void setRandomInjuryChanceTest() {
        Injury object = new Injury();
        object.setRandomInjuryChance(0.05);
        assertEquals(object.getRandomInjuryChance(),0.05,0);
    }

    @Test
    public void getInjuryDaysLowTest() {
        Injury object = new Injury();
        object.setInjuryDaysLow(1);
        assertSame(object.getInjuryDaysLow(),1);
    }

    @Test
    public void getInjuryDaysHighTest() {
        Injury object = new Injury();
        object.setInjuryDaysHigh(120);
        assertSame(object.getInjuryDaysHigh(),120);
    }

    @Test
    public void isRandomInjuryChanceNotValid() {
        Injury object = new Injury();
        object.setRandomInjuryChance(1.5);
        assertFalse(object.isRandomInjuryChanceNotValid(object.getRandomInjuryChance()));
        object.setRandomInjuryChance(-5);
        assertTrue(object.isRandomInjuryChanceNotValid(object.getRandomInjuryChance()));
    }

    @Test
    public void isRandomInjuryChanceNotValidTest() {
        Injury object = new Injury(0.05,1,235);
        assertFalse(object.isInjuryDaysHighValueNotValid(object.getInjuryDaysLow(),object.getInjuryDaysHigh()));
        object = new Injury(0.05,200,150);
        assertTrue(object.isInjuryDaysHighValueNotValid(object.getInjuryDaysLow(),object.getInjuryDaysHigh()));
    }

    @Test
    public void validateTest() {
        Injury object = new Injury(-5,1,200);
        assertSame(InjuryConstant.RandomInjuryChanceError,object.validate());
        object = new Injury(0.05,200,1);
        assertSame(InjuryConstant.InjuryDaysError, object.validate());
        object = new Injury(0.05,1,213);
        assertSame(InjuryConstant.Success, object.validate());
    }


}

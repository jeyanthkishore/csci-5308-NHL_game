package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InjuryTest {

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
    public void isRandomInjuryChanceNotValidTest() {
        Injury object = new Injury();
        object.setRandomInjuryChance(1.5);
        assertFalse(object.isRandomInjuryChanceNotValid(object.getRandomInjuryChance()));
        object.setRandomInjuryChance(-5);
        assertTrue(object.isRandomInjuryChanceNotValid(object.getRandomInjuryChance()));
    }

    @Test
    public void validateTest() {
        Injury object = new Injury();
        object.setRandomInjuryChance(-5);
        object.setInjuryDaysHigh(200);
        object.setInjuryDaysLow(1);
        assertSame(InjuryConstant.RandomInjuryChanceError,object.validate());
        object = new Injury();
        object.setRandomInjuryChance(0.05);
        object.setInjuryDaysHigh(1);
        object.setInjuryDaysLow(200);
        assertSame(InjuryConstant.InjuryDaysError, object.validate());
        object.setRandomInjuryChance(0.05);
        object.setInjuryDaysHigh(200);
        object.setInjuryDaysLow(1);
        assertSame(InjuryConstant.Success, object.validate());
    }


}

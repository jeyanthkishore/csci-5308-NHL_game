package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

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


}

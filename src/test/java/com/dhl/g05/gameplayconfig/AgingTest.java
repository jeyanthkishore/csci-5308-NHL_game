package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class AgingTest {

    @Test
    public void parameterConstructorTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        Aging object = new Aging(data.averageRetirementAge, data.maximumAge);
        assertSame(data.averageRetirementAge,object.getAverageRetirementAge());
        assertSame(data.maximumAge, object.getMaximumAge());
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

}

package com.dhl.g05.leaguemodel;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class CoachObjectTest {

    @Test
    public void constructorTest() {
        CoachObject object = new CoachObject();
        assertNull(object.getName());
    }

    @Test
    public void setNameTest() {
        CoachObject object = new CoachObject();
        object.setName("Smith");
        assertSame(object.getName(),"Smith");
    }

    @Test
    public void getNameTest() {
        CoachObject object = new CoachObject();
        object.setName("Smith");
        assertSame(object.getName(),"Smith");
    }

    @Test
    public void setSkatingTest() {
        CoachObject object = new CoachObject();
        object.setSkating(0.5);
        assertEquals(object.getSkating(),0.5,0);
    }

    @Test
    public void getSkatingTest() {
        CoachObject object = new CoachObject();
        object.setSkating(0.5);
        assertEquals(object.getSkating(),0.5,0);
    }

    @Test
    public void setShootingTest() {
        CoachObject object = new CoachObject();
        object.setShooting(0.5);
        assertEquals(object.getShooting(),0.5,0);
    }

    @Test
    public void getShootingTest() {
        CoachObject object = new CoachObject();
        object.setShooting(0.5);
        assertEquals(object.getShooting(),0.5,0);
    }

    @Test
    public void setCheckingTest() {
        CoachObject object = new CoachObject();
        object.setChecking(0.5);
        assertEquals(object.getChecking(),0.5,0);
    }

    @Test
    public void getCheckingTest() {
        CoachObject object = new CoachObject();
        object.setChecking(0.5);
        assertEquals(object.getChecking(),0.5,0);
    }

    @Test
    public void setSavingTest() {
        CoachObject object = new CoachObject();
        object.setSaving(0.5);
        assertEquals(object.getSaving(),0.5,0);
    }

    @Test
    public void getSavingTest() {
        CoachObject object = new CoachObject();
        object.setSaving(0.5);
        assertEquals(object.getSaving(),0.5,0);
    }

    @Test
    public void isCoachNameNullTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        data.setCoachNameNull();
        CoachObject object = new CoachObject(data);
        assertTrue(object.isCoachNameNull());
    }

    @Test
    public void isCoachNameEmptyTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        data.setCoachNameEmpty();
        CoachObject object = new CoachObject(data);
        assertTrue(object.isCoachNameEmpty());
    }

    @Test
    public void validateStatsTest() {
        CoachObject validate = new CoachObject();
        validate.setSkating(0.7);
        assertEquals(validate.validateStat(0.7),true);
    }

    @Test
    public void validateStatsInverseTest() {
        FreeAgentObject validate = new FreeAgentObject();
        validate.setSkating(-1);
        assertEquals(validate.validateStat(-1),false);
    }

    @Test
    public void isCoachStatValidTest() {
        CoachObject validate = new CoachObject();
        validate.setSkating(0.5);
        validate.setShooting(0.3);
        validate.setChecking(0.5);
        validate.setSaving(0.6);
        assertEquals(validate.isCoachStatValid(),true);
    }

    @Test
    public void isCoachStatInValidTest() {
        CoachObject validate = new CoachObject();
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
        CoachObject validate = new CoachObject(mock);
        assertEquals("Coach Name Should Not have Empty Value",validate.validate());
        mock = new JsonMockDataDb();
        validate = new CoachObject(mock);
        validate.setSkating(-1);
        assertEquals("Invalid state of coach",validate.validate());
    }

}


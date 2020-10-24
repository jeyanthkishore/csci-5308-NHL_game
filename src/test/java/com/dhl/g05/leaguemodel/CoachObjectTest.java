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
    public void getResultTest() {
        CoachObject object = new CoachObject();
        object.setResult("success");
        assertEquals("success",object.getResult());
    }
    @Test
    public void setResultTest() {
        CoachObject object = new CoachObject();
        object.setResult("success");
        assertEquals("success",object.getResult());
    }


}


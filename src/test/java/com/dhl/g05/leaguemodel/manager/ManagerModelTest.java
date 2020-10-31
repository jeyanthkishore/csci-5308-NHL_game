package com.dhl.g05.leaguemodel.manager;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerModelTest {

    @Test
    public void constructorTest() {
        ManagerModel object = new ManagerModel();
        assertNull(object.getName());
    }

    @Test
    public void parameterConstructorTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        ManagerModel object = new ManagerModel(data.generalManagerName);
        assertSame(data.generalManagerName,object.getName());
    }

    @Test
    public void managerModelReferenceConstructorTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        ManagerModel object = new ManagerModel(data.generalManagerName);
        assertSame(data.generalManagerName,object.getName());
    }

    @Test
    public void getNameTest() {
        ManagerModel object = new ManagerModel();
        object.setName("Smith");
        assertSame(object.getName(),"Smith");
    }

    @Test
    public void setNameTest() {
        ManagerModel object = new ManagerModel();
        object.setName("Smith");
        assertSame(object.getName(),"Smith");
    }

    @Test
    public void managerNameNullTest() {
        JsonMockDataDb mock = new JsonMockDataDb();
        mock.setManagerNameNull();
        ManagerModel object= new ManagerModel(mock);
        assertTrue(object.isManagerNameNull());
    }

    @Test
    public void managerNameEmptyTest() {
        JsonMockDataDb mock = new JsonMockDataDb();
        mock.setManagerNameEmpty();
        ManagerModel object= new ManagerModel(mock);
        assertTrue(object.isManagerNameEmpty());
    }

    @Test
    public void validateTest() {
        JsonMockDataDb mock = new JsonMockDataDb();
        mock.setManagerNameEmpty();
        ManagerModel validate = new ManagerModel(mock);
        assertSame(ManagerConstant.ManagerNameEmpty,validate.validate());
        mock = new JsonMockDataDb();
        mock.setManagerNameNull();
        validate = new ManagerModel(mock);
        assertSame(ManagerConstant.ManagerNameEmpty,validate.validate());
    }

}

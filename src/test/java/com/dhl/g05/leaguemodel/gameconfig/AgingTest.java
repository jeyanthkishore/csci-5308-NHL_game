package com.dhl.g05.leaguemodel.gameconfig;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.gameplayconfig.Aging;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class AgingTest {

    @Test
    public void parameterConstructorTest() {
        JsonMockDataDb data = new JsonMockDataDb();
        Aging object = new Aging(data.averageRetirementAge, data.maximumAge);
        assertSame(data.averageRetirementAge,object.getAverageRetirementAge());
        assertSame(data.maximumAge, object.getMaximumAge());
    }
}

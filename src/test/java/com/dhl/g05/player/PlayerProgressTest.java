package com.dhl.g05.player;

import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import com.dhl.g05.mockdata.JsonMockDataDb;

import static org.mockito.Mockito.when;

public class PlayerProgressTest {
    private static IRandomGeneratorFactory randomGeneratorFactoryMock;
    private static IPlayerProgress playerProgress;
    private static PlayerModel player;

    @BeforeClass
    public static void setup() {
        randomGeneratorFactoryMock = Mockito.mock(RandomGeneratorFactory.class);
        playerProgress =new PlayerProgress(randomGeneratorFactoryMock);
        player = new PlayerModel();
    }

    @Test
    public void isInjuredTest() {
        IInjury injury = new Injury();
        injury.setRandomInjuryChance(0.5);
        injury.setInjuryDaysHigh(200);
        injury.setInjuryDaysLow(1);
        player.setInjuredStatus(true);
        Assert.assertTrue(playerProgress.isInjured(player,injury));
        player.setInjuredStatus(false);
        Mockito.when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,1)).thenReturn(0.9);
        Mockito.when(randomGeneratorFactoryMock.roundDoubleNumber(0.9,2)).thenReturn(0.9);
        Assert.assertTrue(playerProgress.isInjured(player,injury));
        player.setInjuredStatus(false);
        Mockito.when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,1)).thenReturn(0.01);
        Mockito.when(randomGeneratorFactoryMock.roundDoubleNumber(0.01,2)).thenReturn(0.01);
        Mockito.when(randomGeneratorFactoryMock.getRandomIntegerNumber(injury.getInjuryDaysLow(),injury.getInjuryDaysHigh())).thenReturn(10);
        playerProgress.isInjured(player,injury);
        Assert.assertTrue(player.getInjuredStatus());
        Assert.assertEquals(10,player.getInjuredForNumberOfDays());
    }

    @Test
    public void isRetiredTest() {
        IAging aging = new Aging();
        aging.setMaximumAge(45);
        aging.setAverageRetirementAge(40);

        PlayerModel player = new PlayerModel();
        player.setAge(45);
        player.setElapsedDaysSinceLastBDay(100);
        player.isRetired(playerProgress, player, aging);
        Assert.assertTrue(player.getRetiredStatus());

        player = new PlayerModel();
        player.setAge(20);
        player.setElapsedDaysSinceLastBDay(1);
        when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,aging.getMaximumAge())).thenReturn(8.1);
        player.isRetired(playerProgress, player, aging);
        Assert.assertFalse(player.getRetiredStatus());

        player = new PlayerModel();
        player.setAge(42);
        player.setElapsedDaysSinceLastBDay(100);
        when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,aging.getMaximumAge())).thenReturn(4.1);
        player.isRetired(playerProgress,player, aging);
        Assert.assertTrue(player.getRetiredStatus());
    }

    @Test
    public void handleFreeAgentRetirementTest() {
        JsonMockDataDb mock = new JsonMockDataDb();
        ILeague league = new LeagueModel(mock);
        IFreeAgent freeAgent = new FreeAgentModel(mock);
        Assert.assertFalse(playerProgress.handleFreeAgentRetirement(freeAgent,league));

        IFreeAgent freeAgentRemove = league.getFreeAgent().get(0);
        playerProgress.handleFreeAgentRetirement(freeAgentRemove, league);
        Assert.assertEquals(mock.freeAgentList.size()-1, league.getFreeAgent().size()-1);
    }

    @Test
    public void handleTeamPlayerRetirementTest() {
        JsonMockDataDb mock = new JsonMockDataDb();
        ILeague league = new LeagueModel(mock);
        PlayerModel player = new PlayerModel(mock);
        ITeam team = new TeamModel(mock);
        Assert.assertFalse(playerProgress.handleTeamPlayerRetirement(player,team,league));
    }

}
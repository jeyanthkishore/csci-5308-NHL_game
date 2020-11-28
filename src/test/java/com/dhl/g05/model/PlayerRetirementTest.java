package com.dhl.g05.model;

import com.dhl.g05.gameplayconfig.AbstractGamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.GamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerRetired;
import com.dhl.g05.model.IRandomNumberFactory;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueModel;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.RandomNumberFactory;
import com.dhl.g05.model.TeamModel;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PlayerRetirementTest {
    private static AbstractPlayerFactory playerFactory;
    private static AbstractGamePlayConfigFactory gamePlayConfigFactory;

    @BeforeClass
    public static void setup() {
        AbstractPlayerFactory.setFactory(new PlayerFactory());
        AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
        playerFactory = AbstractPlayerFactory.getFactory();
        gamePlayConfigFactory = AbstractGamePlayConfigFactory.getFactory();
    }

    @Test
    public void checkPlayerRetirementTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IPlayerRetired playerRetired = playerFactory.getPlayerRetirement();
        IPlayer player = playerFactory.getPlayer();
        IAging aging = gamePlayConfigFactory.getAging();
        player.setAge(50);
        aging.setMaximumAge(40);
        assertTrue(playerRetired.checkPlayerRetirement(aging,player));
        player.setAge(30);
        aging.setAverageRetirementAge(35);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(15,0)).thenReturn(7);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(aging.getAverageRetirementAge(),0)).thenReturn(31);
        playerRetired.checkPlayerRetirement(aging,player);
        player.setAge(35);
        aging.setAverageRetirementAge(30);
        aging.setMaximumAge(50);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(50,16)).thenReturn(17);
        when(randomNumberFactoryMock.generateRandomIntegerNumber(aging.getMaximumAge(),aging.getAverageRetirementAge())).thenReturn(35);
        playerRetired.checkPlayerRetirement(aging,player);
    }

    @Test
    public void isFreeAgentsRetiredTest() {
        LeagueMockData mock = new LeagueMockData();
        ILeague league = new LeagueModel(mock);
        IFreeAgent freeAgent = new FreeAgentModel(mock);
        IPlayerRetired playerRetired = playerFactory.getPlayerRetirement();
        assertTrue(playerRetired.isFreeAgentsRetired(league,freeAgent));
    }

    @Test
    public void isPlayerRetiredTest() {
        LeagueMockData mock = new LeagueMockData();
        ILeague league = new LeagueModel(mock);
        ITeam team = new TeamModel(mock);
        IPlayer player = new PlayerModel(mock);
        IPlayerRetired playerRetired = playerFactory.getPlayerRetirement();
        assertTrue(playerRetired.isPlayerRetired(league,player,team));
    }
}

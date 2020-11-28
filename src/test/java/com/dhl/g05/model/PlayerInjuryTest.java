package com.dhl.g05.model;

import com.dhl.g05.gameplayconfig.AbstractGamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.GamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerInjured;
import com.dhl.g05.model.IRandomNumberFactory;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.model.RandomNumberFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PlayerInjuryTest {
    private static IPlayerInjured playerInjured;
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
    public void checkPlayerInjuryTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        playerInjured = playerFactory.getPlayerInjury();
        IInjury injury = gamePlayConfigFactory.getInjury();
        IPlayer player = playerFactory.getPlayer();
        injury.setInjuryDaysHigh(100);
        injury.setInjuryDaysLow(10);
        injury.setRandomInjuryChance(1.5);
        player.setInjuryStatus(true);
        assertTrue(playerInjured.checkPlayerInjury(player,injury));
        player.setInjuryStatus(false);
        when(randomNumberFactoryMock.generateRandomDoubleNumber(0,1)).thenReturn(0.01);
        assertTrue(playerInjured.checkPlayerInjury(player,injury));
    }
}

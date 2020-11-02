package com.dhl.g05.player;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.gameplayconfig.Injury;

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
}

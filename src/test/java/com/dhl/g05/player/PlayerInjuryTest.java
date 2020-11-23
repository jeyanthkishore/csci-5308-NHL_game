package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.gameplayconfig.Injury;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PlayerInjuryTest {
    private static IPlayerInjured playerInjured = new PlayerInjury();

    @Test
    public void checkPlayerInjuryTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IInjury injury = new Injury();
        IPlayer player = new PlayerModel();
        injury.setInjuryDaysHigh(100);
        injury.setInjuryDaysLow(10);
        injury.setRandomInjuryChance(1.5);
        player.setInjuredStatus(true);
        assertTrue(playerInjured.checkPlayerInjury(player,injury));

        player.setInjuredStatus(false);
        when(randomNumberFactoryMock.generateRandomDoubleNumber(0,1)).thenReturn(0.01);
        assertTrue(playerInjured.checkPlayerInjury(player,injury));
    }
}

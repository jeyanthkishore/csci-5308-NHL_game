package com.dhl.g05.player;

import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.IAging;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class PlayerRetirementTest {

    @Test
    public void checkPlayerRetirementTest() {
        IRandomNumberFactory randomNumberFactoryMock = Mockito.mock(RandomNumberFactory.class);
        IPlayerRetired playerRetired = new PlayerRetirement();
        PlayerModel player = new PlayerModel();
        IAging aging = new Aging();
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
}

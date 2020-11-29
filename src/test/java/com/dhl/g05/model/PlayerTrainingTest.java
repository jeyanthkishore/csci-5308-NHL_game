/*
package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerTraining;
import com.dhl.g05.model.IRandomNumberFactory;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.model.PlayerTraining;
import com.dhl.g05.model.RandomNumberFactory;

public class PlayerTrainingTest {
	
    private static IPlayerTraining playerTraining;
    private static IRandomNumberFactory randomGeneratorFactoryMock;

    @BeforeClass
    public static void setup() {
    	AbstractPlayerFactory.setFactory(new PlayerFactory());
        randomGeneratorFactoryMock = Mockito.mock(RandomNumberFactory.class);
        playerTraining =new PlayerTraining(randomGeneratorFactoryMock);
    }

	@Test
	public void performanceCheckTest() {
		PlayerTrainingMockData data = new PlayerTrainingMockData();
		ILeague league = data.leagueObject;
		Mockito.when(randomGeneratorFactoryMock.generateRandomDoubleNumber(0,1)).thenReturn(0.4);
		for (IConference conference : league.getConferenceDetails()) {
    		for (IDivision division : conference.getDivisionDetails()) {
    			for (ITeam team : division.getTeamDetails()) {
    				for(IPlayer player : team.getPlayerList()) {
    					playerTraining.performTrainingForPlayer(player,team.getCoachDetails(),league);
    				}
    			}
    		}
    	}
		assertEquals("Same",11.0,data.player.getSkating(),0.0);
		assertEquals("Same",13.0,data.player.getShooting(),0.0);
		assertEquals("Same",12.0,data.player.getChecking(),0.0);
		assertEquals("Same",13.0,data.player.getSaving(),0.0);
    }
	
	@Test
	public void performanceCheckInjuryTest() {
		PlayerTrainingMockData data = new PlayerTrainingMockData();
		ILeague league = data.leagueObject;
		Mockito.when(randomGeneratorFactoryMock.generateRandomDoubleNumber(0,1)).thenReturn(0.8);
		for (IConference conference : league.getConferenceDetails()) {
    		for (IDivision division : conference.getDivisionDetails()) {
    			for (ITeam team : division.getTeamDetails()) {
    				for(IPlayer player : team.getPlayerList()) {
    					playerTraining.performTrainingForPlayer(player,team.getCoachDetails(),league);
    				}
    			}
    		}
    	}
		assertEquals("Same",10.0,data.player.getSkating(),0.0);
		assertEquals("Same",12.0,data.player.getShooting(),0.0);
		assertEquals("Same",11.0,data.player.getChecking(),0.0);
		assertEquals("Same",12.0,data.player.getSaving(),0.0);
    }
}
*/

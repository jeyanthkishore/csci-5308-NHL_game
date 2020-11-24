package com.dhl.g05.Training;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.IRandomNumberFactory;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.player.RandomNumberFactory;
import com.dhl.g05.team.ITeam;

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

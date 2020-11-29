package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.dhl.g05.ApplicationConfiguration;

public class PlayerTrainingTest {
	
    private static IPlayerTraining playerTraining;
    private static IRandomNumberFactory randomGeneratorFactoryMock;

    @BeforeClass
    public static void setup() {
    	playerTraining = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerTraining();
        randomGeneratorFactoryMock = Mockito.mock(RandomNumberFactory.class);
        playerTraining.setRandomGeneratorFactory(randomGeneratorFactoryMock);
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
    					playerTraining.performTrainingForPlayer(player,team.getCoachDetails(),league.getGamePlayConfig().getInjuries());
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
    					playerTraining.performTrainingForPlayer(player,team.getCoachDetails(),league.getGamePlayConfig().getInjuries());
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


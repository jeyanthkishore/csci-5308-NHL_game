package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import java.util.List;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;

public class StrongTeamTest {

	private static IStrongTeam strongTeam;
	IWeakTeam weakTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createWeakteam();
	MockLeagueModel mockLeague = new MockLeagueModel();
	WeakTeamTest weakTest = new WeakTeamTest();
	ITeam strongestTeam = mockLeague.leagueMock4();

	@Test
	public void setStrongTeamTest() {
		strongTeam =ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrongTeam(strongestTeam);
		assertEquals(strongestTeam, strongTeam.getStrongTeam());
	}

	@Test
	public void getWeakTeamTest() {
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrongTeam(mockLeague.leagueMock4());
		assertNotSame(mockLeague.leagueMock4(), strongTeam.getStrongTeam());
	}

	@Test
	public void setConferenceNameTest() {
		IConference conference = new ConferenceModel();
		strongTeam =ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		conference.setConferenceName("Western");
		strongTeam.setConferenceName(conference.getConferenceName());
		assertSame(strongTeam.getConferenceName(), conference.getConferenceName());
	}

	@Test
	public void setDivisionNameTest() {
		IDivision division = new DivisionModel();
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		division.setDivisionName("Indian");
		strongTeam.setDivisionName(division.getDivisionName());
		assertSame(strongTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void getDivisionNameTest() {
		IDivision division = new DivisionModel();
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		division.setDivisionName("Pacific");
		strongTeam.setDivisionName(division.getDivisionName());
		assertSame(strongTeam.getDivisionName(), division.getDivisionName());
	}

	@Test
	public void setStrongestPlayersToTradeTest() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrongestPlayersToTrade(mockLeague.leagueMock2());
		assertSame(strongTeam.getStrongestPlayersToTrade().size(), 2);
	}

	@Test
	public void getStrongestPlayersToTradeTest1() {
		MockLeagueModel mockLeague = new MockLeagueModel();
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrongestPlayersToTrade(mockLeague.leagueMock3());
		assertSame(strongTeam.getStrongestPlayersToTrade().size(), 1);
	}

	@Test
	public void getStrengthOfStrongestPlayersTest1() {
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrengthOfStrongestPlayers(5);
		assertNotSame(strongTeam.getStrengthOfStrongestPlayers(), 4);
	}

	@Test
	public void getStrengthOfStrongestPlayersTest2() {
		strongTeam =ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrengthOfStrongestPlayers(5);
		assertEquals(strongTeam.getStrengthOfStrongestPlayers(), 5, 0);
	}

	@Test
	public void setStrengthOfStrongestPlayersTest() {
		strongTeam = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createStrongteam();
		strongTeam.setStrengthOfStrongestPlayers(8);
		assertEquals(strongTeam.getStrengthOfStrongestPlayers(), 8, 0);
	}

	@Test
	public void findTeamToSwapTest() {
		ILeague league = mockLeague.leagueMock();
		weakTeam= mockLeague.weakTeamMock();
		boolean result = strongTeam.findTeamToSwap(league, weakTeam);
		String expectedTeamName = strongTeam.getStrongTeam().getTeamName();
		List<IPlayer> position = strongTeam.getStrongestPlayersToTrade();
		assertEquals("Rythm", expectedTeamName);
		assertEquals(result, true);
		assertEquals("player1Team2", ((FreeAgentModel) position.get(0)).getPlayerName());
		assertEquals("defense", position.get(0).getPosition());
	}
 
	@Test
	public void findTeamToSwapNoStrongTeamTest() {
		ILeague league = mockLeague.leagueMock1();
		weakTeam= mockLeague.weakTeamMock();
		boolean result = strongTeam.findTeamToSwap(league, weakTeam);
		assertSame(result,false);
	}

}

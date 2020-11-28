package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;

public class SwapPlayersTest {

	ISwapPlayers swap = AbstractTradingFactory.instance().getSwapplayers();
	MockLeagueModel mockLeague = new MockLeagueModel();
	List<ITeam> teams = new ArrayList<>();
	List<IPlayer> swapList1 = new ArrayList<>();
	List<IPlayer> swapList2 = new ArrayList<>();

	@Test
	public void swapPlayersTest1() {
		teams = mockLeague.leagueMock6();
		swapList1.add(teams.get(0).getPlayerList().get(0));
		swapList1.add(teams.get(0).getPlayerList().get(1));
		swapList2.add(teams.get(1).getPlayerList().get(0));
		swapList2.add(teams.get(1).getPlayerList().get(1));
		swap.swapPlayers(teams.get(0), teams.get(1), swapList1, swapList2);
		assertSame(teams.get(0).getPlayerList().size(), teams.get(1).getPlayerList().size());
	}

	@Test
	public void swapPlayersTest2() {
		teams = mockLeague.leagueMock6();
		swapList1.add(teams.get(0).getPlayerList().get(2));
		swapList2.add(teams.get(1).getPlayerList().get(1));
		swap.swapPlayers(teams.get(0), teams.get(1), swapList1, swapList2);
		assertEquals(((FreeAgentModel) teams.get(0).getPlayerList().get(2)).getPlayerName(), "JustinTeam2");
	}
}

package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.TeamModel;

public class ResolveTradeTest {
	private static final String FORWARD = "Forward";
	private static final String DEFENSE = "Defense";
	private static IResolveTrade resolveTrade;
	private static AbstractTradingFactory abstractTradingFactory;

	@BeforeClass
	public static void setup() {
		AbstractTradingFactory.setFactory(new TradingFactory());
		abstractTradingFactory = AbstractTradingFactory.getFactory();
	}

	@Test
	public void dropWeakestPlayersToFreeAgentListTest() {
		resolveTrade = abstractTradingFactory.getResolveTrade();
		IPlayer player1 = new PlayerModel();
		((FreeAgentModel) player1).setPlayerName("Shawn");
		player1.setPosition(DEFENSE);
		player1.setPlayerStrength(10);
		player1.setChecking(5);
		player1.setShooting(5);
		player1.setSkating(5);
		IPlayer player2 = new PlayerModel();
		((FreeAgentModel) player2).setPlayerName("Mendes");
		player2.setPosition(FORWARD);
		player2.setPlayerStrength(5);
		player2.setChecking(2);
		player2.setShooting(2);
		player2.setSkating(2);
		List<IPlayer> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		ITeam team = new TeamModel();
		team.setPlayerList(players);
		resolveTrade.dropToFreeAgentList(team, DEFENSE, 1);
		assertEquals(team.getPlayerList().size(), 2);
	}

}

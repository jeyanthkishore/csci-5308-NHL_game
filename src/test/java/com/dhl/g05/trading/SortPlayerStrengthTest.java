package com.dhl.g05.trading;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IPlayer;

public class SortPlayerStrengthTest {

	@Test
	public void sortByAscendingTest() {
		ISortPlayerStrength sort =ApplicationConfiguration.instance().getTradingConcreteFactoryState().createSortplayerstrength();
		IPlayer player1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1).setPlayerName("Brian");
		player1.setPlayerStrength(8);
		IPlayer player2 =ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2).setPlayerName("James");
		player2.setPlayerStrength(10);
		IPlayer player3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player3).setPlayerName("Lily");
		player3.setPlayerStrength(6);

		List<IPlayer> playerDetails = new ArrayList<>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		List<IPlayer> expectedAscending = sort.sortByAscending(playerDetails);

		List<IPlayer> actualAsc = new ArrayList<>();
		actualAsc.add(player3);
		actualAsc.add(player1);
		actualAsc.add(player2);

		assertTrue(expectedAscending.equals(actualAsc));
	}

	@Test
	public void sortByDescendingTest() {
		ISortPlayerStrength sort =  ApplicationConfiguration.instance().getTradingConcreteFactoryState().createSortplayerstrength();
		IPlayer player1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1).setPlayerName("Brian");
		player1.setPlayerStrength(8);
		IPlayer player2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		player2.setPlayerName("James");
		player2.setPlayerStrength(10);
		IPlayer player3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player3).setPlayerName("Lily");
		player3.setPlayerStrength(6);

		List<IPlayer> playerDetails = new ArrayList<>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);

		List<IPlayer> expectedDescending = sort.sortByDescending(playerDetails);
		List<IPlayer> actualDesc = new ArrayList<>();
		actualDesc.add(player2);
		actualDesc.add(player1);
		actualDesc.add(player3);

		assertTrue(expectedDescending.equals(actualDesc));
	}

}

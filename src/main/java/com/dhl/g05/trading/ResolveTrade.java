package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class ResolveTrade implements IResolveTrade{
	// set loss count to zero
	// check if captain or not;
	// make captain
	// set the new value to the team.
	public void resolveTrade(TeamModel weakTeam, TeamModel strongTeam) {

		List<PlayerModel> afterTradeWeakTeam = checkNumberOfPlayers(weakTeam);
		weakTeam.setPlayerList(afterTradeWeakTeam);
		weakTeam.setPlayerList(checkCaptain(weakTeam));
		List<PlayerModel> afterTradeStrongTeam = checkNumberOfPlayers(strongTeam);
		strongTeam.setPlayerList(afterTradeStrongTeam);
		strongTeam.setPlayerList(checkCaptain(strongTeam));
		
	}

	// check if 20 players are there
	public List<PlayerModel> checkNumberOfPlayers(TeamModel team) {
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();
		List<PlayerModel> newPlayers1 = new ArrayList<PlayerModel>(); ;
		int totalPlayers = team.getPlayerList().size();
		if (totalPlayers > 20 && team.getUserTeam()) {
			if (team.getUserTeam()) {
				// for user hire from free agent . call the team creation method. Ensure there
				// are in 18+2 combo
				// check for 1 captain
				// newPlayers=;
			} else {
				newPlayers1 = (sortPlayer.sortByDescending(team.getPlayerList())).subList(0, 20);
				// check they are in 18+2 combo
				// make one captain
				// newPlayers=;
			}
		}
		if (totalPlayers < 20) {
			if (team.getUserTeam()) {
				// display the player to pick from free agent list
				// check the number of players to be 18+2 combo
			} else {
				// hire the best players from the free agent
			}
		}
		return newPlayers1;
	}

	//check if team has a single captain
	public List<PlayerModel> checkCaptain(TeamModel team) {
			ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();
			long numberOfcaptain= team.containOneTeamCaptain();
			if(numberOfcaptain>1 )
		     {
		    	for(int i=1;i<numberOfcaptain;i++)
		    	{
		    		(team.getPlayerList().get(i)).setCaptain(false);
		    	}
		     }
			else
			{
				(sortPlayer.sortByDescending(team.getPlayerList()).get(0)).setCaptain(true);
			}
			
			return team.getPlayerList();
				
		}

}

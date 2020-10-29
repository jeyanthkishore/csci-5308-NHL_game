package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public interface IResolveTrade {

	public void resolveTrade(TeamModel weakTeam, TeamModel strongTeam);

	public List<PlayerModel> checkNumberOfPlayers(TeamModel team);

	public List<PlayerModel> checkCaptain(TeamModel team);

}

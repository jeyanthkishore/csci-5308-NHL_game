package com.dhl.g05.db;

import com.dhl.g05.coach.CoachPersistence;
import com.dhl.g05.coach.ICoachModelPersistence;
import com.dhl.g05.conference.ConferencePersistence;
import com.dhl.g05.conference.IConferenceModelPersistence;
import com.dhl.g05.division.DivisionPersistence;
import com.dhl.g05.division.IDivisionModelPersistence;
import com.dhl.g05.freeagent.FreeAgentPersistence;
import com.dhl.g05.freeagent.IFreeAgentPersistence;
import com.dhl.g05.gameplayconfig.GamePlayPersistence;
import com.dhl.g05.gameplayconfig.IGameConfigPersistence;
import com.dhl.g05.league.ILeagueModelPersistence;
import com.dhl.g05.league.LeaguePersistence;
import com.dhl.g05.player.IPlayerModelPersistence;
import com.dhl.g05.player.PlayerPersistence;
import com.dhl.g05.team.ITeamModelPersistence;
import com.dhl.g05.team.TeamPersistence;

public class DataBaseFactory extends AbstractDataBaseFactory{

	@Override
	public ILeagueModelPersistence getLeagueDatabase() {
		return new LeaguePersistence();
	}

	@Override
	public ICoachModelPersistence getCoachDatabase() {
		return new CoachPersistence();
	}

	@Override
	public IConferenceModelPersistence getConferenceDatabase() {
		return new ConferencePersistence();
	}

	@Override
	public IDivisionModelPersistence getDivisionDatabase() {
		return new DivisionPersistence();
	}

	@Override
	public ITeamModelPersistence getTeamDatabase() {
		return new TeamPersistence();
	}

	@Override
	public IPlayerModelPersistence getPlayerDatabase() {
		return new PlayerPersistence();
	}

	@Override
	public IFreeAgentPersistence getFreeAgentDatabase() {
		return new FreeAgentPersistence();
	}

	@Override
	public IGameConfigPersistence getGameConfigDatabase() {
		return new GamePlayPersistence();
	}

}

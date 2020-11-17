package com.dhl.g05.db;

import com.dhl.g05.coach.ICoachModelPersistence;
import com.dhl.g05.conference.IConferenceModelPersistence;
import com.dhl.g05.division.IDivisionModelPersistence;
import com.dhl.g05.freeagent.IFreeAgentPersistence;
import com.dhl.g05.gameplayconfig.IGameConfigPersistence;
import com.dhl.g05.league.ILeagueModelPersistence;
import com.dhl.g05.player.IPlayerModelPersistence;
import com.dhl.g05.team.ITeamModelPersistence;

public abstract class AbstractDataBaseFactory {
	private static AbstractDataBaseFactory abstractDatabaseFactory;

	public static AbstractDataBaseFactory getFactory() {
		return abstractDatabaseFactory;
	}

	public static void setFactory(AbstractDataBaseFactory databaseFactory) {
		abstractDatabaseFactory = databaseFactory;
	}
	
	public abstract ILeagueModelPersistence getLeagueDatabase();
	public abstract ICoachModelPersistence getCoachDatabase();
	public abstract IConferenceModelPersistence getConferenceDatabase();
	public abstract IDivisionModelPersistence getDivisionDatabase();
	public abstract ITeamModelPersistence getTeamDatabase();
	public abstract IPlayerModelPersistence getPlayerDatabase();
	public abstract IFreeAgentPersistence getFreeAgentDatabase();
	public abstract IGameConfigPersistence getGameConfigDatabase();
}

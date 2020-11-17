package filehandler;

import com.dhl.g05.coach.CoachPersistenceMock;
import com.dhl.g05.coach.ICoachModelPersistence;
import com.dhl.g05.conference.ConferencePersistenceMock;
import com.dhl.g05.conference.IConferenceModelPersistence;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.division.DivisionPersistenceMock;
import com.dhl.g05.division.IDivisionModelPersistence;
import com.dhl.g05.freeagent.FreeAgentPersistenceMock;
import com.dhl.g05.freeagent.IFreeAgentPersistence;
import com.dhl.g05.gameplayconfig.GamePlayConfigPersistenceMock;
import com.dhl.g05.gameplayconfig.IGameConfigPersistence;
import com.dhl.g05.league.ILeagueModelPersistence;
import com.dhl.g05.league.LeaguePersistenceMock;
import com.dhl.g05.player.IPlayerModelPersistence;
import com.dhl.g05.player.PlayerPersistenceMock;
import com.dhl.g05.team.ITeamModelPersistence;
import com.dhl.g05.team.TeamPersistenceMock;

public class DatabaseMockFactory extends AbstractDataBaseFactory{
	
	@Override
	public ILeagueModelPersistence getLeagueDatabase() {
		return new LeaguePersistenceMock();
	}

	@Override
	public ICoachModelPersistence getCoachDatabase() {
		return new CoachPersistenceMock();
	}

	@Override
	public IConferenceModelPersistence getConferenceDatabase() {
		return new ConferencePersistenceMock();
	}

	@Override
	public IDivisionModelPersistence getDivisionDatabase() {
		return new DivisionPersistenceMock();
	}

	@Override
	public ITeamModelPersistence getTeamDatabase() {
		return new TeamPersistenceMock();
	}

	@Override
	public IPlayerModelPersistence getPlayerDatabase() {
		return new PlayerPersistenceMock();
	}

	@Override
	public IFreeAgentPersistence getFreeAgentDatabase() {
		return new FreeAgentPersistenceMock();
	}

	@Override
	public IGameConfigPersistence getGameConfigDatabase() {
		return new GamePlayConfigPersistenceMock();
	}
}

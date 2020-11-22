package com.dhl.g05.statemachine;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.filehandler.ILeagueModelJson;

public class StateMachineFactory extends AbstractStateMachineFactory{
	
	private final IPlayerCommunication communication;
    private final ILeagueModelJson leagueModel;
	
    public StateMachineFactory(
    		IPlayerCommunication communication,
    		ILeagueModelJson leagueModel) {
        this.communication = communication;
        this.leagueModel = leagueModel;
    }

    
    @Override
    public IStateMachine getStateMachine(AbstractState abstractState) {
        return new StateMachine(abstractState);
    }
    
	@Override
	public AbstractState getImportState() {
		return new ImportState(communication);
	}

	@Override
	public AbstractState getCreateTeamState() {
		return new CreateTeamState(communication);
	}

	@Override
	public AbstractState getLoadTeamState() {
		return new LoadTeamState(communication,leagueModel);
	}

	@Override
	public AbstractState getPlayerChoiceState() {
		return new PlayerChoiceState(communication);
	}

	@Override
	public AbstractState getStimulateState(int seasons) {
		return new SimulateState(seasons);
	}

	@Override
	public AbstractState getInitializeSeasonState() {
		return new InitializeSeasonState(communication);
	}


	@Override
	public AbstractState getAdvancedTimeState() {
		return new AdvanceTimeState();
	}


	@Override
	public AbstractState getTrainingState() {
		return new TrainingState();
	}


	@Override
	public AbstractState getPlayOffState() {
		return new PlayoffScheduleState();
	}


	@Override
	public AbstractState getAgingState() {
		return new AgingState();
	}


	@Override
	public AbstractState getTradeState() {
		return new TradeState();
	}


	@Override
	public AbstractState getStimulateGameState() {
		return new StimulateGameState();
	}


	@Override
	public AbstractState getAdvanceToNextSeasonState() {
		return new AdvanceToNextSeasonState();
	}


	@Override
	public AbstractState getPersistState() {
		return new PersistState();
	}
	
}

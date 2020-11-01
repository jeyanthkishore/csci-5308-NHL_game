package com.dhl.g05.statemachine;


public class LoadTeamState extends AbstractState{

	private String teamName;

	public LoadTeamState(StateMachine stateMachine) {
		super(stateMachine);
	}

	@Override
	public boolean enter() {

		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team name:");
		teamName = this.getOuterStateMachine().getPlayerCommunication().getResponse();

		return true;
	}

	@Override
	public boolean performStateTask() {
		ILeagueModelJson leagueModel = this.getOuterStateMachine().getLeagueModel();
		if (leagueModel.loadTeam(teamName)) {
			return true;
		} else {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Team does not exist");
			return false;
		}
	}

	@Override
	public boolean exit() {
		this.setNextState(new PlayerChoiceState(this.getOuterStateMachine(),"Enter number of seasons to simulate", new SimulateState(this.getOuterStateMachine())));
		return true;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String name) {
		this.teamName = name;
	}



}

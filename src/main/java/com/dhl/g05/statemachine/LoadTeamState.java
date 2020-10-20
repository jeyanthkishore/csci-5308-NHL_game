package com.dhl.g05.statemachine;


public class LoadTeamState extends AbstractState{

	private String teamName;
	private String leagueName;
	private String conferenceName;
	private String divisionName;
	
	public LoadTeamState(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public boolean enter() {
		
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter league name:");
		leagueName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter conference name:");
		conferenceName = this.getOuterStateMachine().getPlayerCommunication().getResponse();

		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter division name:");
		divisionName = this.getOuterStateMachine().getPlayerCommunication().getResponse();

		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team name:");
		teamName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
	
		return true;
	}

	@Override
	public boolean performStateTask() {
		ILeagueModel leagueModel = this.getOuterStateMachine().getLeagueModel();
		if (leagueModel.loadTeam(leagueName, conferenceName, divisionName, teamName)) {
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

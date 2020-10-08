package com.dhl.g05.statemachine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.dhl.g05.LeagueModelCreator;

public class ImportState extends AbstractState{
	private LeagueModelCreator creator;
	private String fileName;
	
	public ImportState(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public void enter() {
		creator = new LeagueModelCreator(this.getOuterStateMachine().getLeagueModel());
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter file name:");
		fileName = this.getOuterStateMachine().getPlayerCommunication().getFile();
		if (fileName.equals("")) {
			this.transitionState(new LoadTeamState(this.getOuterStateMachine()));
		}
		
		performStateTask();
	}

	@Override
	public void performStateTask() {
		try {
			creator.createLeagueFromFile(fileName);
			if(this.getOuterStateMachine().getLeague()==null) {
				//TODO: get accurate error
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("League model not created");
			} else {
				exit();
			}
		} catch (FileNotFoundException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("File not found\n");
		} catch (IOException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(e.getLocalizedMessage());
		} catch (ParseException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(e.getLocalizedMessage());
		} 
		
		this.enter();
		
	}

	@Override
	public void exit() {
		// TODO persist to data base and confirm successful
		this.getOuterStateMachine().getLeagueModel().persistLeague();
		this.transitionState(new PlayerChoiceState(super.getOuterStateMachine()));
		super.getOuterStateMachine().enterState();
	}

	public String getFileName() {
		return fileName;
	}

}

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
		creator = new LeagueModelCreator(this.getOuterStateMachine().getLeagueModel());
	}
	
	@Override
	public boolean enter() {
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter file name:");
		fileName = this.getOuterStateMachine().getPlayerCommunication().getFile();
		if (fileName.equals("")) {
			this.setNextState(new LoadTeamState(this.getOuterStateMachine()));
			this.markStateCompleted();
		}
		
		return true;
	}

	@Override
	public boolean performStateTask() {
		try {
			creator.createLeagueFromFile(fileName);
			if(this.getOuterStateMachine().getLeague()==null) {
				//TODO: get accurate error
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("League model not created");
			} else {
				return true;
			}
		} catch (FileNotFoundException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("File not found\n");
		} catch (IOException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(e.getLocalizedMessage());
		} catch (ParseException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(e.getLocalizedMessage());
		} 
		
		return false;
		
	}

	@Override
	public boolean exit() {
		// TODO persist to data base and confirm successful
		if (this.getOuterStateMachine().getLeagueModel().persistLeague()) {
			this.setNextState(new PlayerChoiceState(super.getOuterStateMachine()));
			this.markStateCompleted();
			return true;
		}
		return false;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String filename) {
		this.fileName = filename;
	}

}

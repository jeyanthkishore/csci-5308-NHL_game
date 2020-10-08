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
	public boolean enter() {
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter a file name to create a new team or hit enter to load and existing team:");
		fileName = this.getOuterStateMachine().getPlayerCommunication().getFile();
		return true;
	}

	@Override
	public boolean performStateTask() {
		if (fileName.equals("")||fileName.isEmpty()) {
			return true;
		}
		
		creator = new LeagueModelCreator(this.getOuterStateMachine().getLeagueModel());
		try {
			creator.createLeagueFromFile(fileName);
			if(this.getOuterStateMachine().getLeague() == null) {
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
		if (fileName.equals("")||fileName.isEmpty()) {
			this.setNextState(new LoadTeamState(this.getOuterStateMachine())); 
		} else {
			this.setNextState(new CreateTeamState(this.getOuterStateMachine()));
		}
		return true;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String filename) {
		this.fileName = filename;
	}

}

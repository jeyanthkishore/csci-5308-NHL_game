package com.dhl.g05.statemachine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class ImportState extends AbstractState{
	private LeagueModelCreatorFromJSON creator;
	private String fileName;
	
	public ImportState(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public boolean enter() {
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter a file name to create a new team or hit enter to load an existing team:");
		fileName = this.getOuterStateMachine().getPlayerCommunication().getFile();
		return true;
	}

	@Override
	public boolean performStateTask() {
		if (fileName.equals("")||fileName.isEmpty()) {
			return true;
		} 
		
		creator = new LeagueModelCreatorFromJSON(this.getOuterStateMachine().getLeagueModel(),this.getOuterStateMachine().getPlayerCommunication());
		
		try {
			
			creator.createLeagueFromFile(fileName);
			
			if (this.getOuterStateMachine().getLeagueModel().getLeague() == null) {
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Issue creating league, try again");
			} else {
				return true;
			}
			
		} catch (FileNotFoundException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("File not found\n");
		} catch (IOException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(e.toString());
		} catch (ParseException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(e.toString());
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

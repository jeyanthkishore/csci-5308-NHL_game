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
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter a file name to create a new team or hit enter to load an existing team:");
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
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Issue creating league, try again");
			} else {
				return true;
			}
		} catch (FileNotFoundException e) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("File not found\n");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
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

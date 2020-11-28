package com.dhl.g05.statemachine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.filehandler.LeagueModelCreatorFromJSON;
import com.dhl.g05.league.LeagueModel;
import com.mysql.cj.util.StringUtils;

public class ImportState extends AbstractState {
	private IPlayerCommunication communication;
	private LeagueModelCreatorFromJSON creator;
	private String fileName;

	public ImportState(IPlayerCommunication communication) {
		this.communication = communication;
	}

	@Override
	public boolean enter() {
		communication.sendMessage("Enter a file name to create a new team or hit enter to load an existing team:");
		fileName = communication.getFile();
		return true;
	}

	@Override
	public boolean performStateTask() {
		if (fileName.equals("")||fileName.isEmpty()) {
			return true;
		} 

		creator = new LeagueModelCreatorFromJSON(communication);

		try {
			LeagueModel league = creator.createLeagueFromFile(fileName);
			if (league == null) {
				this.setLeague(null);
				communication.sendMessage("Issue creating league, try again");
			} else {
				this.setLeague(league);
				return true;
			}

		} catch (FileNotFoundException e) {
			communication.sendMessage("File not found\n");
		} catch (IOException e) {
			communication.sendMessage(e.toString());
		} catch (ParseException e) {
			communication.sendMessage(e.toString());
		}

		return false;

	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		if (StringUtils.isNullOrEmpty(fileName)) {
			this.setNextState(stateFactory.getLoadTeamState()); 
		} else {
			this.setNextState(stateFactory.getCreateTeamState());
		}
		return true;
	}

}

package com.dhl.g05.simulation.statemachine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.ITeamDatabaseOperation;
import com.dhl.g05.model.CoachConstant;
import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.mysql.cj.util.StringUtils;

public class CreateTeamState extends AbstractState {
	
	static final Logger logger = LogManager.getLogger(CreateTeamState.class);
	private ITeam newTeam;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private ILeague league;
	private IPlayerCommunication communicate;
	private List<IFreeAgent> freeAgentList = new ArrayList<>();
	private List<ICoach> coachList = new ArrayList<>();
	private List<String> managerList = new ArrayList<>();
	private ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();

	public CreateTeamState(IPlayerCommunication communication) {
		communicate = communication;
		league = this.getLeague();
	}

	@Override
	public boolean enter() {
		logger.info("Asking user to enter details for creating new team");
		Boolean teamNotEntered = true;
		communicate.sendMessage(CreateTeamConstant.CreateNewTeam.getValue());
		communicate.sendMessage(CreateTeamConstant.EnterConference.getValue());
		conferenceName = communicate.getResponse();
		communicate.sendMessage(CreateTeamConstant.EnterDivision.getValue());
		divisionName = communicate.getResponse();
		while(teamNotEntered) {
			communicate.sendMessage(CreateTeamConstant.EnterTeam.getValue());
			teamName =  communicate.getResponse();
			ITeam team = modelFactory.createTeamModel();
			DatabaseAbstractFactory database = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
			ITeamDatabaseOperation checkTeam = database.createTeamDatabaseOperation();
			boolean notUnique = team.isTeamExist(teamName,checkTeam);
			if(notUnique) {
				logger.info("User entered Team Name alreadty exists");
				communicate.sendMessage(CreateTeamConstant.EnterUniqueName.getValue());
				continue;
			}
			teamNotEntered = false;
		}
		return true; 
	}

	@Override
	public boolean performStateTask() {

		if (StringUtils.isNullOrEmpty(teamName) || StringUtils.isNullOrEmpty(divisionName)
				|| StringUtils.isNullOrEmpty(conferenceName)){
			logger.info("Required details missing from user");
			communicate.sendMessage(CreateTeamConstant.MissingField.getValue());
			return false;
		}

		if  (isDivisionConferenceNotExists()) {
			logger.info("Division Conference combo does not exist");
			communicate.sendMessage(CreateTeamConstant.ComboDoesnotExist.getValue());
			return false;
		}

		if(createOperation()){
			this.setLeague(league);
			this.setCurrentUserTeam(teamName);
			return true;
		}

		return false;
	}

	private Boolean createOperation() {

		if(teamCreation()){
			league.setFreeAgent(freeAgentList);
			league.setFreeCoach(coachList);
			league.setManagerList(managerList);
			addNewTeamtoLeagueObject();
			return true;
		}
		return false;
	}

	private boolean teamCreation() {
		String managerObject = "";
		List<IPlayer> playerList = new ArrayList<>();
		freeAgentList = league.getFreeAgent();
		newTeam = modelFactory.createTeamModel();
		newTeam.setTeamName(teamName);

		ICoach coach = pickCoach();
		if(coach.validate().equals(CoachConstant.Success)) {
			newTeam.setCoachDetails(coach);
		} else {
			logger.info("Error exist in coach object");
			communicate.sendMessage(CreateTeamConstant.ErrorCoachCreation.getValue());
			return false;
		}

		managerObject = pickManager();
		if(StringUtils.isNullOrEmpty(managerObject)) {
			logger.info("Manager name is empty");
			communicate.sendMessage(CreateTeamConstant.ErrorManagerCreation.getValue());
			return false;
		} else {
			newTeam.setGeneralManagerName(managerObject);
		}

		playerList = pickPlayers();
		if(playerList.size()<30 || playerList.size()>30) {
			logger.info("Error in creating PlayerList");
			communicate.sendMessage(CreateTeamConstant.ErrorPlayerCreation.getValue());
			return false;
		} else {
			newTeam.setPlayerList(playerList);
		}

		newTeam.setUserTeam(true);
		return true;
	}

	private ICoach pickCoach() {
		logger.info("Coach selection begins");
		ICoach selectedCoach = modelFactory.createCoachModel();
		coachList = league.getFreeCoach();
		Boolean coachNotSelected = true;
		int number;

		while(coachNotSelected) {
			communicate.sendMessage(CreateTeamConstant.SelectCoach.getValue());
			communicate.displayCoachList(coachList);
			communicate.sendMessage(CreateTeamConstant.AddCoach.getValue());
			try {
				number = communicate.getResponseNumber();
				System.out.println(number);
			}catch(InputMismatchException e) {
				logger.warn("User entered a string instead of number");
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				continue;
			}
			if(number ==0 || number>coachList.size()) {
				logger.info("Selected coach number is out of range");
				communicate.sendMessage(CreateTeamConstant.InvalidNumber.getValue());
				continue;
			}
			selectedCoach = coachList.get(number-1);
			coachList.remove(number-1);
			coachNotSelected = false;
		}

		return selectedCoach;
	}

	private String pickManager() {
		logger.info("Manager Selection Begins");
		managerList = league.getManagerList();
		Boolean ManagerNotSelected = true;
		String selectedManager ="";
		int number;
		while(ManagerNotSelected) {
			communicate.sendMessage(CreateTeamConstant.SelectManager.getValue());
			communicate.displayManagerList(managerList);
			communicate.sendMessage(CreateTeamConstant.AddManager.getValue());
			try {
				number = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				logger.warn("User entered a string instead of number");
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				continue;
			}
			if(number == 0 || number>managerList.size()) {
				logger.info("Selected manager number is out of range");
				communicate.sendMessage(CreateTeamConstant.InvalidNumber.getValue());
				continue;
			}
			selectedManager = managerList.get(number-1);
			managerList.remove(number-1);
			ManagerNotSelected = false;
		}

		return selectedManager;
	}

	private List<IPlayer> pickPlayers() {
		logger.info("Team Player Selection Begins");
		List<IPlayer> playerList = new ArrayList<>();
		Boolean captainNotAssigned = true;
		String captainResponse ="";
		Boolean captain = false;
		int goalie = 0;
		int skaters = 0;
		int responseNumber;
		while(playerList.size()<30) {
			String teamCount = CreateTeamConstant.TeamCount.getValue() + playerList.size();
			String skaterscount = CreateTeamConstant.SkaterCount.getValue() +skaters;
			String goaliecount = CreateTeamConstant.GoalieCount.getValue() +goalie;
			communicate.sendMessage(teamCount+CreateTeamConstant.Separator.getValue()
			+skaterscount+CreateTeamConstant.Separator.getValue()+goaliecount);
			communicate.sendMessage(CreateTeamConstant.SelectFreeAgent.getValue());
			communicate.displayFreeAgentList(freeAgentList);
			communicate.sendMessage(CreateTeamConstant.AddPlayer.getValue());
			try {
				responseNumber = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				logger.warn("User entered a string instead of number");
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				continue;
			}

			if(responseNumber == 0 || responseNumber>freeAgentList.size()) {
				logger.info("Selected free Agent is out of range");
				communicate.sendMessage(CreateTeamConstant.InvalidNumber.getValue());
				continue;
			}
			if(captainNotAssigned) {
				communicate.sendMessage(CreateTeamConstant.CaptainConfirmation.getValue());
				captainResponse = communicate.getResponse();
				if(captainResponse.equalsIgnoreCase(CreateTeamConstant.Yes.getValue())) {
					captain = true;
					captainNotAssigned = false;
				}
			}
			if(freeAgentList.get(responseNumber-1).getPosition().equals(CreateTeamConstant.Goalie.getValue())) {
				goalie++;
				if(goalie > 4) {
					goalie--;
					communicate.sendMessage(CreateTeamConstant.MaximumGoalieMessage.getValue());
					continue;
				}
			}else {
				skaters++;
				if(skaters > 26) {
					skaters--;
					communicate.sendMessage(CreateTeamConstant.MaximumSkatersMessage.getValue());
					continue;
				}
			}
			IPlayer player = modelFactory.createPlayerModel();
			player.convertFreeAgentToPlayer(freeAgentList.get(responseNumber-1), captain);
			playerList.add(player);
			captain = false;
			freeAgentList.remove(responseNumber-1);
		}

		return playerList;
	}


	private void addNewTeamtoLeagueObject() {
		logger.info("Adding new team to leage object");
		List<IConference> conferences = league.getConferenceDetails();
		for (IConference c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<IDivision> divisions = c.getDivisionDetails();
				for (IDivision d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						d.getTeamDetails().add(newTeam);
						break;
					}
				}
			}
		}
	}

	private boolean isDivisionConferenceNotExists() {
		logger.info("Checking Division Conference combo");
		List<IConference> conferences = league.getConferenceDetails();
		for (IConference c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<IDivision> divisions = c.getDivisionDetails();
				for (IDivision d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createPlayerChoiceState());
		return true;
	}

}

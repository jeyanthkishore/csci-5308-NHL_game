package com.dhl.g05.simulation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.database.DatabaseAbstractFactory;
import com.dhl.g05.database.ITeamDatabaseOperation;
import com.dhl.g05.model.CoachConstant;
import com.dhl.g05.model.CoachModel;
import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.TeamModel;
import com.mysql.cj.util.StringUtils;

public class CreateTeamState extends AbstractState {

	private ITeam newTeam;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private ILeague league;
	private IPlayerCommunication communicate;
	private List<IFreeAgent> freeAgentList = new ArrayList<>();
	private List<ICoach> coachList = new ArrayList<>();
	private List<String> managerList = new ArrayList<>();

	public CreateTeamState(IPlayerCommunication communication) {
		communicate = communication;
		league = this.getLeague();
	}

	@Override
	public boolean enter() {
		Boolean teamNotEntered = true;
		communicate.sendMessage("Creating a New Team");
		communicate.sendMessage("Enter conference name:");
		conferenceName = communicate.getResponse();
		communicate.sendMessage("Enter division name:");
		divisionName = communicate.getResponse();
		while(teamNotEntered) {
			communicate.sendMessage("Enter team name:");
			teamName =  communicate.getResponse();
			ITeam team = new TeamModel();
			DatabaseAbstractFactory database = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
			ITeamDatabaseOperation checkTeam = database.createTeamDatabaseOperation();
			boolean notUnique = team.isTeamExist(teamName,checkTeam);
			if(notUnique) {
				communicate.sendMessage("Please Enter Unique Team Name");
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
			communicate.sendMessage("Missing feild, team not created");
			return false;
		}

		if  (isDivisionConferenceNotExists()) {
			communicate.sendMessage("Conference/Division combo does not exist in current league ");
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
		List<IPlayer> playerList = new ArrayList<>();
		freeAgentList = league.getFreeAgent();
		newTeam = new TeamModel();
		newTeam.setTeamName(teamName);

		ICoach coach = pickCoach();
		if(coach.validate().equals(CoachConstant.Success)) {
			newTeam.setCoachDetails(coach);
		} else {
			communicate.sendMessage(CreateTeamConstant.ErrorCoachCreation.getValue());
			return false;
		}

		String managerObject = "";
		managerObject = pickManager();
		if(StringUtils.isNullOrEmpty(managerObject)) {
			communicate.sendMessage(CreateTeamConstant.ErrorManagerCreation.getValue());
			return false;
		} else {
			newTeam.setGeneralManagerName(managerObject);
		}

		playerList = pickPlayers();
		if(playerList.size()<20 || playerList.size()>20) {
			communicate.sendMessage(CreateTeamConstant.ErrorPlayerCreation.getValue());
			return false;
		} else {
			newTeam.setPlayerList(playerList);
		}

		newTeam.setUserTeam(true);
		return true;
	}

	private ICoach pickCoach() {
		ICoach selectedCoach = new CoachModel();
		coachList = league.getFreeCoach();
		Boolean coachNotSelected = true;
		int number;

		while(coachNotSelected) {
			communicate.sendMessage(CreateTeamConstant.SelectCoach.getValue());
			communicate.sendCoachMessage(coachList);
			communicate.sendMessage(CreateTeamConstant.AddCoach.getValue());
			try {
				number = communicate.getResponseNumber();
				System.out.println(number);
			}catch(InputMismatchException e) {
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				continue;
			}
			if(number ==0 || number>coachList.size()) {
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
		managerList = league.getManagerList();
		Boolean ManagerNotSelected = true;
		String selectedManager ="";
		int number;
		while(ManagerNotSelected) {
			communicate.sendMessage(CreateTeamConstant.SelectManager.getValue());
			communicate.sendManagerMessage(managerList);
			communicate.sendMessage(CreateTeamConstant.AddManager.getValue());
			try {
				number = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				continue;
			}
			if(number == 0 || number>managerList.size()) {
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
		List<IPlayer> playerList = new ArrayList<>();
		Boolean captainNotAssigned = true;
		String captainResponse ="";
		Boolean captain = false;
		int goalie = 0;
		int skaters = 0;
		int responseNumber;
		double skating = 0;
		double shooting = 0;
		double checking = 0;
		double saving = 0;
		int birthDay=0;
		int birthMonth=0;
	    int birthYear =0;
		String name ="";
		String position="";
		while(playerList.size()<20) {
			String teamCount = CreateTeamConstant.TeamCount.getValue() + playerList.size();
			String skaterscount = CreateTeamConstant.SkaterCount.getValue() +skaters;
			String goaliecount = CreateTeamConstant.GoalieCount.getValue() +goalie;
			communicate.sendMessage(teamCount+CreateTeamConstant.Separator.getValue()
			+skaterscount+CreateTeamConstant.Separator.getValue()+goaliecount);
			communicate.sendMessage(CreateTeamConstant.SelectFreeAgent.getValue());
			communicate.sendMessage(freeAgentList);
			communicate.sendMessage(CreateTeamConstant.AddPlayer.getValue());
			try {
				responseNumber = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				continue;
			}

			if(responseNumber == 0 || responseNumber>freeAgentList.size()) {
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
			name = freeAgentList.get(responseNumber-1).getPlayerName();
			position = freeAgentList.get(responseNumber-1).getPosition();
			checking = freeAgentList.get(responseNumber-1).getChecking();
			skating = freeAgentList.get(responseNumber-1).getSkating();
			shooting = freeAgentList.get(responseNumber-1).getShooting();
			saving = freeAgentList.get(responseNumber-1).getSaving();
			birthDay = freeAgentList.get(responseNumber-1).getBirthDay();
			birthMonth = freeAgentList.get(responseNumber-1).getBirthMonth();
			birthYear = freeAgentList.get(responseNumber-1).getBirthYear();
			playerList.add(new PlayerModel(name,position,captain,checking,skating,shooting,saving,birthDay,birthMonth,birthYear));
			captain = false;
			freeAgentList.remove(responseNumber-1);
		}

		return playerList;
	}
	
	
	private void addNewTeamtoLeagueObject() {
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
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(stateFactory.createPlayerChoiceState());
		return true;
	}

}

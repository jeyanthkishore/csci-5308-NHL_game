package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.dhl.g05.simulation.statemachine.IGameResolver;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.ITradingConfig;
import com.dhl.g05.simulation.statemachine.ITraining;

class PlayerTrainingMockData {

	private static ModelAbstractFactory modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	private static SimulationAbstractFactory simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	public ILeague leagueObject;
	public IPlayer player;
	public int averageRetirementAge = 35;
	public int maximumAge = 60;
	public double statDecayChance=0.05;
	public double randownWinChance = 0.1;
	public double randomInjuryChance = 0.05;
	public int injuryDaysLow = 1;
	public int injuryDaysHigh = 120;
	public int daysUntilTraining = 100;
	public int lossPoint = 8;
	public double randomTradeOffer = 0.05;
	public double randomAcceptanceChance = 0.05;
	public int maxPlayerPerTrade = 2;
	public IGamePlayConfig gamePlayConfig;
	public ITradingConfig tradeConfig;
	public ITraining training;
	public IGameResolver gameResolver;
	public IInjury injury;
	public IAging aging;
	
	public PlayerTrainingMockData() {
		leagueObject = modelAbstractFactory.createLeagueModel();
		player = modelAbstractFactory.createPlayerModel();
		player.setPlayerName("Lebron James");
		player.setPosition("forward");
		player.setCaptain(true);
		player.setSkating(10);
		player.setShooting(12);
		player.setChecking(11);
		player.setSaving(12);
		ICoach coach = modelAbstractFactory.createCoachModel();
		coach.setName("Livingstone");
		coach.setSkating(0.5);
		coach.setShooting(0.6);
		coach.setChecking(0.7);
		coach.setSaving(0.8);
		List<IPlayer> playerList = new ArrayList<>();
		playerList.add(player);
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setTeamName("Rollers");
		team.setCoachDetails(coach);
		team.setPlayerList(playerList);
		team.setGeneralManagerName("GamerLord");
		List<ITeam> teamList = new ArrayList<>();
		teamList.add(team);
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName("Wrong Division");
		division.setTeamDetails(teamList);
		List<IDivision> divList = new ArrayList<>();
		divList.add(division);
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName("One Conference");
		conference.setDivisionDetails(divList);
		List<IConference> conList = new ArrayList<>();
		conList.add(conference);
		leagueObject.setConferenceDetails(conList);
		tradeConfig = simulationAbstractFactory.createTradingConfig();
		tradeConfig.setLossPoint(lossPoint);
		tradeConfig.setMaxPlayersPerTrade(maxPlayerPerTrade);
		tradeConfig.setRandomTradeOfferChance(randomTradeOffer);
		tradeConfig.setRandomAcceptanceChance(randomAcceptanceChance);
		training = simulationAbstractFactory.createTrainingConfig();
		training.setDaysUntilStatIncreaseCheck(daysUntilTraining);
		gameResolver = simulationAbstractFactory.createGameResolverConfig();
		gameResolver.setRandomWinChance(randownWinChance);
		injury = simulationAbstractFactory.createInjuryConfig();
		injury.setInjuryDaysHigh(injuryDaysHigh);
		injury.setInjuryDaysLow(injuryDaysLow);
		injury.setRandomInjuryChance(randomInjuryChance);
		aging = simulationAbstractFactory.createAgingConfig();
		aging.setAverageRetirementAge(averageRetirementAge);
		aging.setMaximumAge(maximumAge);
		aging.setStatDecayChance(statDecayChance);
		gamePlayConfig = simulationAbstractFactory.createGamePlayConfig();
		gamePlayConfig.setAgingConfig(aging);
		gamePlayConfig.setGameResolverConfig(gameResolver);
		gamePlayConfig.setInjuriesConfig(injury);
		gamePlayConfig.setTradingConfig(tradeConfig);
		gamePlayConfig.setTrainingConfig(training);
		leagueObject.setGamePlayConfig(gamePlayConfig);
	}
	
}

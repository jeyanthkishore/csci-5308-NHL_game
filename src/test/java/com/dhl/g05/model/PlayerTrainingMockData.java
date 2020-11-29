package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.simulation.AgingConfig;
import com.dhl.g05.simulation.GamePlayConfigModel;
import com.dhl.g05.simulation.GameResolverConfig;
import com.dhl.g05.simulation.IGamePlayConfig;
import com.dhl.g05.simulation.InjuryConfig;
import com.dhl.g05.simulation.TradingConfig;
import com.dhl.g05.simulation.TrainingConfig;class PlayerTrainingMockData {

	public LeagueModel leagueObject;
	public PlayerModel player;
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
	public TradingConfig tradeConfig;
	public TrainingConfig training;
	public GameResolverConfig gameResolver;
	public InjuryConfig injury;
	public AgingConfig aging;
	
	public PlayerTrainingMockData() {
		leagueObject = new LeagueModel();
		player = new PlayerModel();
		player.setPlayerName("Lebron James");
		player.setPosition("forward");
		player.setCaptain(true);
		player.setSkating(10);
		player.setShooting(12);
		player.setChecking(11);
		player.setSaving(12);
		ICoach coach = new CoachModel();
		coach.setName("Livingstone");
		coach.setSkating(0.5);
		coach.setShooting(0.6);
		coach.setChecking(0.7);
		coach.setSaving(0.8);
		List<IPlayer> playerList = new ArrayList<>();
		playerList.add(player);
		ITeam team = new TeamModel();
		team.setTeamName("Rollers");
		team.setCoachDetails(coach);
		team.setPlayerList(playerList);
		team.setGeneralManagerName("GamerLord");
		List<ITeam> teamList = new ArrayList<>();
		teamList.add(team);
		IDivision division = new DivisionModel();
		division.setDivisionName("Wrong Division");
		division.setTeamDetails(teamList);
		List<IDivision> divList = new ArrayList<>();
		divList.add(division);
		ConferenceModel conference = new ConferenceModel();
		conference.setConferenceName("One Conference");
		conference.setDivisionDetails(divList);
		List<IConference> conList = new ArrayList<>();
		conList.add(conference);
		leagueObject.setConferenceDetails(conList);
		tradeConfig = new TradingConfig();
		tradeConfig.setLossPoint(lossPoint);
		tradeConfig.setMaxPlayersPerTrade(maxPlayerPerTrade);
		tradeConfig.setRandomTradeOfferChance(randomTradeOffer);
		tradeConfig.setRandomAcceptanceChance(randomAcceptanceChance);
		training = new TrainingConfig();
		training.setDaysUntilStatIncreaseCheck(daysUntilTraining);
		gameResolver = new GameResolverConfig();
		gameResolver.setRandomWinChance(randownWinChance);
		injury = new InjuryConfig();
		injury.setInjuryDaysHigh(injuryDaysHigh);
		injury.setInjuryDaysLow(injuryDaysLow);
		injury.setRandomInjuryChance(randomInjuryChance);
		aging = new AgingConfig();
		aging.setAverageRetirementAge(averageRetirementAge);
		aging.setMaximumAge(maximumAge);
		aging.setStatDecayChance(statDecayChance);
		gamePlayConfig = new GamePlayConfigModel();
		gamePlayConfig.setAging(aging);
		gamePlayConfig.setGameResolver(gameResolver);
		gamePlayConfig.setInjuries(injury);
		gamePlayConfig.setTrading(tradeConfig);
		gamePlayConfig.setTraining(training);
		leagueObject.setGamePlayConfig(gamePlayConfig);
	}
	
}

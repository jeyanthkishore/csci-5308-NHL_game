package com.dhl.g05.Training;

import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.GameResolverConfig;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.gameplayconfig.TrainingConfig;class PlayerTrainingMockData {

	public LeagueModel leagueObject;
	public PlayerModel player;
	public int averageRetirementAge = 35;
	public int maximumAge = 60;
	public double randownWinChance = 0.1;
	public double randomInjuryChance = 0.05;
	public int injuryDaysLow = 1;
	public int injuryDaysHigh = 120;
	public int daysUntilTraining = 100;
	public int lossPoint = 8;
	public double randomTradeOffer = 0.05;
	public double randomAcceptanceChance = 0.05;
	public int maxPlayerPerTrade = 2;
	public GamePlayConfigModel gamePlayConfig;
	public TradingModel tradeConfig;
	public TrainingConfig training;
	public GameResolverConfig gameResolver;
	public Injury injury;
	public Aging aging;
	
	public PlayerTrainingMockData() {
		leagueObject = new LeagueModel();
		player = new PlayerModel("Lebron James", "forward", true, 33, 10, 12, 11, 12);
		CoachModel coach = new CoachModel("Livingstone", 0.5, 0.6, 0.7, 0.8);
		List<PlayerModel> playerList = new ArrayList<PlayerModel>();
		playerList.add(player);
		TeamModel team = new TeamModel("Rollers", coach, "GamerLord", playerList);
		List<TeamModel> teamList = new ArrayList<TeamModel>();
		teamList.add(team);
		DivisionModel division = new DivisionModel("Wrong Division", teamList);
		List<DivisionModel> divList = new ArrayList<DivisionModel>();
		divList.add(division);
		ConferenceModel conference = new ConferenceModel("One Conference", divList);
		List<ConferenceModel> conList = new ArrayList<ConferenceModel>();
		conList.add(conference);
		leagueObject.setConferenceDetails(conList);
		tradeConfig = new TradingModel(lossPoint, randomTradeOffer, maxPlayerPerTrade, randomAcceptanceChance);
		training = new TrainingConfig(daysUntilTraining);
		gameResolver = new GameResolverConfig(randownWinChance);
		injury = new Injury(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		aging = new Aging(averageRetirementAge, maximumAge);
		gamePlayConfig = new GamePlayConfigModel(tradeConfig, aging, injury, gameResolver, training);
		leagueObject.setGamePlayConfig(gamePlayConfig);
	}
	
}
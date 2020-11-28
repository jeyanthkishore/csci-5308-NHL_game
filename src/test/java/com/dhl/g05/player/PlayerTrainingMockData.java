package com.dhl.g05.player;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.GameResolverConfig;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.gameplayconfig.TradingConfig;
import com.dhl.g05.gameplayconfig.TrainingConfig;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;class PlayerTrainingMockData {

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
	public GamePlayConfigModel gamePlayConfig;
	public TradingConfig tradeConfig;
	public TrainingConfig training;
	public GameResolverConfig gameResolver;
	public Injury injury;
	public Aging aging;
	
	public PlayerTrainingMockData() {
		leagueObject = new LeagueModel();
		player = new PlayerModel("Lebron James", "forward", true,10, 12, 11, 12,22,10,1997);
		CoachModel coach = new CoachModel("Livingstone", 0.5, 0.6, 0.7, 0.8);
		List<IPlayer> playerList = new ArrayList<>();
		playerList.add(player);
		ITeam team = new TeamModel("Rollers", coach, "GamerLord", playerList);
		List<ITeam> teamList = new ArrayList<>();
		teamList.add(team);
		DivisionModel division = new DivisionModel("Wrong Division", teamList);
		List<IDivision> divList = new ArrayList<>();
		divList.add(division);
		ConferenceModel conference = new ConferenceModel("One Conference", divList);
		List<IConference> conList = new ArrayList<>();
		conList.add(conference);
		leagueObject.setConferenceDetails(conList);
		tradeConfig = new TradingConfig(lossPoint, randomTradeOffer, maxPlayerPerTrade, randomAcceptanceChance);
		training = new TrainingConfig(daysUntilTraining);
		gameResolver = new GameResolverConfig(randownWinChance);
		injury = new Injury(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		aging = new Aging(averageRetirementAge, maximumAge,statDecayChance);
		gamePlayConfig = new GamePlayConfigModel(tradeConfig, aging, injury, gameResolver, training);
		leagueObject.setGamePlayConfig(gamePlayConfig);
	}
	
}

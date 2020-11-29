package com.dhl.g05.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayerDraft;
import com.dhl.g05.model.PlayerDraft;

public class PlayerDraftState extends AbstractState {

	private ILeague league;
	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		IPlayerDraft playerDraft = new PlayerDraft();
		ILeagueStanding standing = league.getLeagueStanding();
		List<IStandingModel> standingList = standing.getRankingAcrossLeague();
		playerDraft.setPickOrderAfterTrading(mockTradePickLatest(standingList));
		playerDraft.playerDraft1(standing);
		
		return true;
	}

	public Map<Integer, List<Map<IStandingModel,IStandingModel>>> mockTradePickLatest(List<IStandingModel> standing) {
		
		List<IStandingModel> standings = standing;
		Map<Integer, List<Map<IStandingModel,IStandingModel>>> finalRoundtradeTeamPick=new HashMap<>();
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound1=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound1= new HashMap<>();
		teamsExchangingPickInRound1.put(standings.get(3), standings.get(1));
		teamsExchangingPickInRound1.put(standings.get(2), standings.get(0));
		listOfTeamsExchangingPickInRound1.add(teamsExchangingPickInRound1);
		finalRoundtradeTeamPick.put(1, listOfTeamsExchangingPickInRound1);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound2=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound2= new HashMap<>();
		teamsExchangingPickInRound2.put(standings.get(1), standings.get(0));
		listOfTeamsExchangingPickInRound2.add(teamsExchangingPickInRound2);
		finalRoundtradeTeamPick.put(2, listOfTeamsExchangingPickInRound2);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound3=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound3= new HashMap<>();
		teamsExchangingPickInRound3.put(standings.get(3), standings.get(2));
		listOfTeamsExchangingPickInRound3.add(teamsExchangingPickInRound3);
		finalRoundtradeTeamPick.put(3, listOfTeamsExchangingPickInRound3);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound4=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound4= new HashMap<>();
		teamsExchangingPickInRound4.put(standings.get(1), standings.get(0));
		listOfTeamsExchangingPickInRound4.add(teamsExchangingPickInRound4);
		finalRoundtradeTeamPick.put(4, listOfTeamsExchangingPickInRound4);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound5=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound5= new HashMap<>();
		teamsExchangingPickInRound5.put(standings.get(1), standings.get(2));
		teamsExchangingPickInRound5.put(standings.get(2), standings.get(0));
		teamsExchangingPickInRound5.put(standings.get(3), standings.get(1));
		listOfTeamsExchangingPickInRound5.add(teamsExchangingPickInRound5);
		finalRoundtradeTeamPick.put(5, listOfTeamsExchangingPickInRound5);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound6=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound6= new HashMap<>();
		teamsExchangingPickInRound6.put(standings.get(1), standings.get(0));
		listOfTeamsExchangingPickInRound6.add(teamsExchangingPickInRound6);
		finalRoundtradeTeamPick.put(6, listOfTeamsExchangingPickInRound6);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound7=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound7= new HashMap<>();
		teamsExchangingPickInRound7.put(standings.get(2), standings.get(3));
		listOfTeamsExchangingPickInRound7.add(teamsExchangingPickInRound7);
		finalRoundtradeTeamPick.put(7, listOfTeamsExchangingPickInRound7);
		
		for (int i = 1; i <=7; i++)
		{
			System.out.println();
			System.out.print("Round" +(i) + " Teams ");
			if(finalRoundtradeTeamPick.get(i) == null)
			{
				continue;
			}
			else
			{
			List<Map<IStandingModel,IStandingModel>> a = finalRoundtradeTeamPick.get(i);
			for(Map<IStandingModel, IStandingModel> list: a)
			{ 
			for (Map.Entry<IStandingModel, IStandingModel> standingTeam : list.entrySet())
			{
				System.out.print(" Team " + standingTeam.getKey().getTeam().getTeamName()+ " pick taken by "+ standingTeam.getValue().getTeam().getTeamName());
			}	
		}
		}
		System.out.println();
		}
		return finalRoundtradeTeamPick;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		this.setNextState(stateFactory.createAdvanceToNextSeasonState());
		return true;
	}

}

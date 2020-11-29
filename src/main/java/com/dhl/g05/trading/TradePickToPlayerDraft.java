package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.simulation.IStandingModel;

public class TradePickToPlayerDraft implements ITradePickToPlayerDraft{
	
	public Map<Integer,List<IStandingModel>> mockTradePick(List<IStandingModel> standing) {
		Map<Integer,List<IStandingModel>> afterTradePickOrder= new HashMap<>() ;
		List<IStandingModel> standings = standing;
		List<IStandingModel> Round1= new ArrayList<>();
		Round1.add(0, standings.get(3));

		List<IStandingModel> Round2= new ArrayList<>();
		Round2.add(0, standings.get(1));
		List<IStandingModel> Round3= new ArrayList<>();
		Round3.add(standings.get(2));
		Round3.add(standings.get(3));
		List<IStandingModel> Round4= new ArrayList<>();
		Round4.add(standings.get(0));
		Round4.add(standings.get(1));
		List<IStandingModel> Round5= new ArrayList<>();

		Round5.add(standings.get(1));
		Round5.add(standings.get(3));
		List<IStandingModel> Round6= null;
		List<IStandingModel> Round7= Round1;
		afterTradePickOrder.put(1, Round1);
		afterTradePickOrder.put(2, Round2);
		afterTradePickOrder.put(3, Round3);
		afterTradePickOrder.put(4, Round4);
		afterTradePickOrder.put(5, Round5);
		afterTradePickOrder.put(6, Round6);
		afterTradePickOrder.put(7, Round7);

		return afterTradePickOrder;
	}

}

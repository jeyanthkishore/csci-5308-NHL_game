package com.dhl.g05.trading;

import java.util.List;
import java.util.Map;

import com.dhl.g05.simulation.IStandingModel;

public interface ITradePickToPlayerDraft {

	public Map<Integer, List<IStandingModel>> mockTradePick(List<IStandingModel> standing);

}

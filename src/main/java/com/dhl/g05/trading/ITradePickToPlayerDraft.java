package com.dhl.g05.trading;

import java.util.List;
import java.util.Map;

import com.dhl.g05.simulation.leaguesimulation.IStandingModel;

public interface ITradePickToPlayerDraft {

	public Map<Integer, List<Map<IStandingModel, IStandingModel>>> mockTradePickLatest(List<IStandingModel> standing);

}

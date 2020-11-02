package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class TradingConfig {

	private static TradingConfig uniqueInstance = null;
	private ICheckLossPoint checklosspoint;
	private ISortPlayerStrength sortplayerstrength;
	private IStrongTeam strongteam;
	private ISwapPlayers swapplayers;
	private IWeakTeam weakteam;
	private IAcceptRejectTrade acceptreject;
	private IResolveTrade resolvetrade;
	// private ITradingModel trade;

	private TradingConfig() {
		checklosspoint = new CheckLossPoint();
		sortplayerstrength = new SortPlayerStrength();
		strongteam = new StrongTeam();
		swapplayers = new SwapPlayers();
		weakteam = new WeakTeam();
		acceptreject = new AcceptRejectTrade();
		resolvetrade=new ResolveTrade() ;
}

	public IResolveTrade getResolvetrade() {
		return resolvetrade;
	}

	public void setResolvetrade(IResolveTrade resolvetrade) {
		this.resolvetrade = resolvetrade;
	}

	public ISortPlayerStrength getSortplayerstrength() {
		return sortplayerstrength;
	}

	public void setSortplayerstrength(ISortPlayerStrength sortplayerstrength) {
		this.sortplayerstrength = sortplayerstrength;
	}

	public IStrongTeam getStrongteam() {
		return strongteam;
	}

	public void setStrongteam(IStrongTeam strongteam) {
		this.strongteam = strongteam;
	}

	public ISwapPlayers getSwapplayers() {
		return swapplayers;
	}

	public void setSwapplayers(ISwapPlayers swapplayers) {
		this.swapplayers = swapplayers;
	}

	public IWeakTeam getWeakteam() {
		return weakteam;
	}

	public void setWeakteam(IWeakTeam weakteam) {
		this.weakteam = weakteam;
	}

	public ICheckLossPoint getChecklosspoint() {
		return checklosspoint;
	}

	public void setChecklosspoint(ICheckLossPoint checklosspoint) {
		this.checklosspoint = checklosspoint;
	}

	public IAcceptRejectTrade getAcceptreject() {
		return acceptreject;
	}

	public void setAcceptreject(IAcceptRejectTrade acceptreject) {
		this.acceptreject = acceptreject;
	}

	public static TradingConfig instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new TradingConfig();
		}
		return uniqueInstance;

	}

}

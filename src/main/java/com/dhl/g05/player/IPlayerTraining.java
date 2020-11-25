package com.dhl.g05.player;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.league.ILeague;

public interface IPlayerTraining {

	public IPlayer performTrainingForPlayer(IPlayer player, ICoach coach, ILeague league);

}

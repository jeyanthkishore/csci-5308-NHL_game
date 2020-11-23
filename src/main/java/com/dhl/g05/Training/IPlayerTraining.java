package com.dhl.g05.Training;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayer;

public interface IPlayerTraining {

	public IPlayer performTrainingForPlayer(IPlayer player, ICoach coach, ILeague league);

}

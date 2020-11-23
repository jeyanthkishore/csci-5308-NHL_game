package com.dhl.g05.Training;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerModel;

public interface IPlayerTraining {

	public IPlayer performTrainingForPlayer(IPlayer player, ICoach coach, LeagueModel league);

}

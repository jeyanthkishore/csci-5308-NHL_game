package com.dhl.g05.Training;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;

public interface IPlayerTraining {

	public PlayerModel performTrainingForPlayer(PlayerModel player, CoachModel headCoach,LeagueModel league);

}

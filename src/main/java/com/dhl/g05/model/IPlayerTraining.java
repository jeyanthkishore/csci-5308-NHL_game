package com.dhl.g05.model;

import com.dhl.g05.simulation.IInjury;

public interface IPlayerTraining {

	public IPlayer performTrainingForPlayer(IPlayer player, ICoach coach, IInjury injury);

	void setRandomGeneratorFactory(IRandomNumberFactory randomGeneratorFactory);

}

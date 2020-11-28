package com.dhl.g05.model;

import java.time.LocalDate;
import java.util.Random;

import com.dhl.g05.statemachine.IAging;

public class PlayerBirthday extends FreeAgentModel implements IPlayerBirthday {

	public void decreaseStatOnBirthday(ILeague league, IAging agingConfig) {
		Random random = new Random();
		for (IConference c : league.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					for (IPlayer p : t.getPlayerList()) {
						if (LocalDate.now().getMonthValue() == p.getBirthMonth() && LocalDate.now().getDayOfMonth() == p.getBirthDay()) {
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setSkating((p.getSkating()) - 1);
							}
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setShooting((p.getShooting()) - 1);
							}
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setChecking((p.getChecking()) - 1);
							}
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setSaving((p.getSaving()) - 1);
							}
						} else
							continue;
					}
				}
			}
		}
	}

}

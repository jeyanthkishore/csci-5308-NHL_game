package com.dhl.g05.player;

import java.time.LocalDate;
import java.util.Random;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.TeamModel;

public class PlayerBirthday extends FreeAgentModel implements IPlayerBirthday {

	public void decreaseStatOnBirthday(ILeague league, IAging agingConfig) {
		Random random = new Random();
		for (ConferenceModel c : league.getConferenceDetails()) {
			for (DivisionModel d : c.getDivisionDetails()) {
				for (TeamModel t : d.getTeamDetails()) {
					for (PlayerModel p : t.getPlayerList()) {
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

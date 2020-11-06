package com.dhl.g05.player;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

import java.time.LocalDate;

public interface IPlayerInjured {

    boolean isPlayerInjured(PlayerInjury playerInjury, IInjury injury);
}

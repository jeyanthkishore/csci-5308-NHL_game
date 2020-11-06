package com.dhl.g05.player;

import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import com.dhl.g05.mockdata.JsonMockDataDb;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.floatThat;
import static org.mockito.Mockito.when;

public class PlayerProgressTest {
    private static IPlayerInjured playerInjured;
    private static PlayerModel player;

    @Test
    public void isPlayerInjuredTest() {
        IInjury injury = new Injury();
        injury.setRandomInjuryChance(0.5);
        injury.setInjuryDaysHigh(200);
        injury.setInjuryDaysLow(1);
        player.setInjuredStatus(true);
        assertTrue(playerInjured.isPlayerInjured(player,injury));
    }
}

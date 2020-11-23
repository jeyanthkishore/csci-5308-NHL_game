package com.dhl.g05.league;

import java.time.LocalDate;
import java.util.List;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.player.IPlayer;

public interface ILeague {

    String getLeagueName();

    void setLeagueName(String league);

    List<IConference> getConferenceDetails();

    void setConferenceDetails(List<IConference> conferencedetail);

    List<IFreeAgent> getFreeAgent();

    void setFreeAgent(List<IFreeAgent> agent);

    List<ICoach> getFreeCoach();

    void setFreeCoach(List<ICoach> freeCoach);

    List<String> getManagerList();

    boolean removeRetiredFreeAgentsFromLeague(IFreeAgent freeAgent);

    List<IFreeAgent> getRetiredFreeAgentsList();

    void setRetiredFreeAgentsList(List<IFreeAgent> retiredFreeAgentsList);

    void setManagerList(List<String> managerList);

    void setDbObject(ILeagueModelPersistence object);

    void addRetiredFreeAgentToList(IFreeAgent freeAgent);

    void addRetiredPlayersToList(IPlayer player);

    GamePlayConfigModel getGamePlayConfig();

    void setGamePlayConfig(GamePlayConfigModel gamePlayConfig);

    LeagueConstant validate();

    List<IPlayer> getRetiredPlayersList();

    void setRetiredPlayersList(List<IPlayer> retiredPlayersList);
}

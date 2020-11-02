package com.dhl.g05.league;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.player.PlayerModel;

import java.util.List;

public interface ILeague {

    String getLeagueName();

    void setLeagueName(String league);

    List<ConferenceModel> getConferenceDetails();

    void setConferenceDetails(List<ConferenceModel> conferencedetail);

    List<FreeAgentModel> getFreeAgent();

    void setFreeAgent(List<FreeAgentModel> agent);

    List<CoachModel> getFreeCoach();

    void setFreeCoach(List<CoachModel> freeCoach);

    List<String> getManagerList();

    void setManagerList(List<String> managerList);

    void setDbObject(ILeagueModelPersistence object);

    GamePlayConfigModel getGamePlayConfig();

    void setGamePlayConfig(GamePlayConfigModel gamePlayConfig);

    int saveLeagueObject(ILeagueModelPersistence database);

    int loadLeagueObject(int leagueId,ILeagueModelPersistence database);

    int loadLeagueFromTeam(String teamName, ILeagueModelPersistence database);

    boolean removeFreeAgentFromLeague(IFreeAgent freeAgent);

    void addRetiredFreeAgent(IFreeAgent freeAgent);

    List<IFreeAgent> getRetiredFreeAgents();

    void setRetiredFreeAgents(List<IFreeAgent> retiredFreeAgents);

    List<PlayerModel> getRetiredTeamPlayers();

    void setRetiredTeamPlayers(List<PlayerModel> retiredTeamPlayers);

    void addRetiredTeamPlayer(PlayerModel player);
}

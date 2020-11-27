package com.dhl.g05.league;

import java.time.LocalDate;
import java.util.List;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.database.IDeserializeModel;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IGamePlayConfig;
import com.dhl.g05.leaguesimulation.ILeagueSchedule;
import com.dhl.g05.leaguesimulation.ILeagueStanding;
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

    void addRetiredFreeAgentToList(IFreeAgent freeAgent);
    
    void addNewFreeAgentsToLeague(List<IFreeAgent> freeAgentList);

    void addRetiredPlayersToList(IPlayer player);

    IGamePlayConfig getGamePlayConfig();

    void setGamePlayConfig(IGamePlayConfig gamePlayConfig);

    LeagueConstant validate();

    List<IPlayer> getRetiredPlayersList();

    void setRetiredPlayersList(List<IPlayer> retiredPlayersList);

	void incrementDaysSinceStatIncrease();

	int getDaysSinceStatIncrease();

	LocalDate getLeagueCurrentDate();

	ILeagueSchedule getLeagueSchedule();

	void incrementCurrentDate();

	void resetDaysSinceStatIncrease();

	void setLeagueCurrentDate(LocalDate currentDate);

	ILeagueStanding getLeagueStanding();

	void setDaysSinceStatIncrease(int days);

	boolean saveLeagueObject(ISerializeModel saveLeague,String teamName);

	void loadLeagueObject(IDeserializeModel loadLeague, String teamName);

}

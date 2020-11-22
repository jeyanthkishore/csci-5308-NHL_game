package com.dhl.g05.team;

import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.coach.ICoach;
import com.dhl.g05.player.IPlayer;

public interface ITeam {

    Boolean getUserTeam();

    void setUserTeam(Boolean userTeam);

    String getTeamName();

    void setTeamName(String teamName);

    String getGeneralManagerName();

    void setGeneralManagerName(String managerName);

    List<IPlayer> getPlayerList();

    void setPlayerList(List<IPlayer> playerList);

    ICoach getCoachDetails();

    void setCoachDetails(ICoach coachDetails);

    double getTeamStrength();

    void setTeamStrength(double teamStrength);

    int saveTeamObject(int divisionId,ITeamModelPersistence database);

    int loadTeamObject(int divisionId,ITeamModelPersistence database);

    boolean checkTeamNotUnique(String teamName,ITeamModelPersistence database);

	TeamConstant validate();
}

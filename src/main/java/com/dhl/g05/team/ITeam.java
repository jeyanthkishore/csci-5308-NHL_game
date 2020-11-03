package com.dhl.g05.team;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.player.PlayerModel;

import java.util.HashMap;
import java.util.List;

public interface ITeam {

    Boolean getUserTeam();

    void setUserTeam(Boolean userTeam);

    String getTeamName();

    void setTeamName(String teamName);

    String getGeneralManagerName();

    void setGeneralManagerName(String managerName);

    List<PlayerModel> getPlayerList();

    void setPlayerList(List<PlayerModel> playerList);

    CoachModel getCoachDetails();

    void setCoachDetails(CoachModel coachDetails);

    double getTeamStrength();

    void setTeamStrength(double teamStrength);

    int saveTeamObject(int divisionId,ITeamModelPersistence database);

    int loadTeamObject(int divisionId,ITeamModelPersistence database);

    List<HashMap<String, Object>> loadAllTeamName(ITeamModelPersistence database);

    boolean removeTeamPlayer(PlayerModel player);
}

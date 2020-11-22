package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.Comparator;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.team.TeamModel;

public class Standing implements IStanding {
	
	private ConferenceModel conference;
	private DivisionModel Division;
	private TeamModel team;
	private int gamesPlayed;
    private int gamesWon;
    private int points;
	
	@Override
	public ConferenceModel getConference() {
		return conference;
	}
	@Override
	public void setConference(ConferenceModel conference) {
		this.conference = conference;
	}
	@Override
	public DivisionModel getDivision() {
		return Division;
	}
	@Override
	public void setDivision(DivisionModel division) {
		Division = division;
	}
	@Override
	public TeamModel getTeam() {
		return team;
	}
	@Override
	public void setTeam(TeamModel team) {
		this.team = team;
	}
	
    @Override
	public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
	public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Override
	public void incrementGamesPlayed() {
        gamesPlayed += 1;
    }

    @Override
	public int getGamesWon() {
        return gamesWon;
    }

    @Override
	public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
	public void incrementGamesWon() {
        gamesWon += 1;
    }

    @Override
	public int getPoints() {
        return points;
    }

    @Override
	public void setPoints(int points) {
        this.points = points;
    }

    @Override
	public void incrementPoints() {
        points += 2;
    }

    public static Comparator<IStanding> standingComparator = (s1, s2) -> {
        int pointsComparison = s2.getPoints() - s1.getPoints();
        int gamesWonComparison = s2.getGamesWon() - s1.getGamesWon();
        int gamesPlayedComparison = s1.getGamesPlayed() - s2.getGamesPlayed();

        if (pointsComparison == 0) {
            if (gamesWonComparison == 0) {
                return gamesPlayedComparison;
            }
            else {
                return gamesWonComparison;
            }
        }

        return pointsComparison;
    };
}

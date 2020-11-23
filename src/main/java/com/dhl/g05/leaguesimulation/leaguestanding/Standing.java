package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.Comparator;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.team.ITeam;

public class Standing implements IStanding {
	
	private IConference conference;
	private IDivision Division;
	private ITeam team;
	private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int points;
	
	@Override
	public IConference getConference() {
		return conference;
	}
	
	@Override
	public void setConference(IConference conference) {
		this.conference = conference;
	}
	
	@Override
	public IDivision getDivision() {
		return Division;
	}
	
	@Override
	public void setDivision(IDivision division) {
		Division = division;
	}
	
	@Override
	public ITeam getTeam() {
		return team;
	}
	
	@Override
	public void setTeam(ITeam team) {
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
  	public void incrementGamesLost() {
    	gamesLost += 1;
      }
    
    @Override
    public int getGamesLost() {
		return gamesLost;
	}
    
    @Override
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
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

    public static Comparator<IStanding> rankingComparator = (s1, s2) -> {
        int pointsComparison = s1.getPoints() - s2.getPoints();
        int gamesWonComparison = s1.getGamesWon() - s2.getGamesWon();
        int gamesPlayedComparison = s1.getGamesPlayed() - s2.getGamesPlayed();

        if (pointsComparison == 0) {
            if (gamesWonComparison == 0) {
                return gamesPlayedComparison;
            }
            else {
                return gamesWonComparison;
            }
        }else {
        	return pointsComparison;
        }

    };
}

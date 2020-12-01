package com.dhl.g05.simulation.leaguesimulation;

import java.util.Comparator;

import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ITeam;
import com.google.gson.annotations.Expose;

public class StandingModel implements IStandingModel {
	
	@Expose
	private IConference conference;
	@Expose
	private IDivision division;
	@Expose
	private ITeam team;
	@Expose
	private int totalGamesPlayed;
	@Expose
    private int numberOfWins;
	@Expose
    private int numberOfLoss;
	@Expose
    private int totalPoints;
	
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
		return division;
	}
	
	@Override
	public void setDivision(IDivision division) {
		this.division = division;
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
	public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    @Override
	public void setTotalGamesPlayed(int gamesPlayed) {
        this.totalGamesPlayed = gamesPlayed;
    }

    @Override
	public void incrementGamesPlayed() {
    	totalGamesPlayed = totalGamesPlayed + 1;
    }

    @Override
	public int getTotalGamesWon() {
        return numberOfWins;
    }

    @Override
	public void setTotalGamesWon(int wins) {
        this.numberOfWins = wins;
    }

    @Override
	public void incrementGamesWon() {
    	numberOfWins = numberOfWins + 1;
    }

    @Override
  	public void incrementGamesLost() {
    	numberOfLoss = numberOfLoss + 1;
      }
    
    @Override
    public int getTotalGamesLost() {
		return numberOfLoss;
	}
    
    @Override
	public void setTotalGamesLost(int loss) {
		this.numberOfLoss = loss;
	}
    
	@Override
	public int getTotalPoints() {
        return totalPoints;
    }

    @Override
	public void setTotalPoints(int points) {
        this.totalPoints = points;
    }

    @Override
	public void incrementPoints() {
    	totalPoints = totalPoints + 2;
    }

    public static Comparator<IStandingModel> rankingComparator = new Comparator<IStandingModel>() {
        @Override
        public int compare(IStandingModel t1, IStandingModel t2) {
        	int pointsDifference = t2.getTotalPoints() - t1.getTotalPoints();
        	int winDifference = t2.getTotalGamesLost() - t1.getTotalGamesLost();
        	if (pointsDifference == 0) {
        		return winDifference;
        	}else {
        		return pointsDifference;
        	}
        }

    };
}

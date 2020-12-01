package com.dhl.g05.simulation.leaguesimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.google.gson.annotations.Expose;

public class LeagueStanding implements ILeagueStanding{
	static final Logger logger = LogManager.getLogger(LeagueStanding.class);
	@Expose
	private List<IStandingModel> standingsList;

	@Override
	public List<IStandingModel> getStandingList () {
		return standingsList;
	}

	@Override
	public void setStandingList(List<IStandingModel> standings) {
		standingsList = standings;
	}

	@Override
	public void createStandingList(ILeague	league) {
		logger.info("Creating New Standing List");
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		standingsList = new ArrayList<>();

		for (IConference conference: league.getConferenceDetails()) {
			for (IDivision division: conference.getDivisionDetails()) {
				for (ITeam team: division.getTeamDetails()) {
					IStandingModel standing = simulationFactory.createStandingModel();
					standing.setConference(conference);
					standing.setDivision(division);
					standing.setTeam(team);
					standingsList.add(standing);
				}
			}
		}
	}

	@Override
	public List<IStandingModel> getRankingAcrossLeague() {
		logger.info("Calculationg Ranking Across League");
		List<IStandingModel> leagueStandings = new ArrayList<>();
		for (IStandingModel standing: standingsList) {
			leagueStandings.add(standing);
		}
		Collections.sort(leagueStandings,StandingModel.rankingComparator);
		return leagueStandings;
	}

	@Override
	public List<IStandingModel> getRankingAcrossDivision(IDivision division) {
		logger.info("Calculationg Ranking Across Division");
		List<IStandingModel> divisionStandings = new ArrayList<>();
		for (IStandingModel standing: standingsList) {
			if (standing.getDivision() == division) {
				divisionStandings.add(standing);
			}
		}
		Collections.sort(divisionStandings,StandingModel.rankingComparator);
		return divisionStandings;
	}

	@Override
	public List<IStandingModel> getRankingAcrossConference(IConference conference) {
		logger.info("Calculationg Ranking Conference League");
		List<IStandingModel> conferenceStandings = new ArrayList<>();
		for (IStandingModel standing: standingsList){
			if (standing.getConference() == conference) {
				conferenceStandings.add(standing);
			}
		}
		Collections.sort(conferenceStandings,StandingModel.rankingComparator);
		return conferenceStandings;
	}

	@Override
	public void updateStatisticsForWinningTeam(IConference conference, IDivision division, ITeam team) {
		logger.info("Updating Standing for Winning Team");
		for (IStandingModel teamstanding: standingsList) {
			if (teamstanding.getConference() == conference && teamstanding.getDivision() == division
					&& teamstanding.getTeam() == team) {
				teamstanding.incrementGamesPlayed();
				teamstanding.incrementGamesWon();
				teamstanding.incrementPoints();
			}
		}
	}

	@Override
	public void updateStatisticsForLosingTeam(IConference conference, IDivision division, ITeam team) {
		logger.info("Updating Standing for Losing Team");
		for (IStandingModel teamstanding: standingsList) {
			if (teamstanding.getConference() == conference && teamstanding.getDivision() == division
					&& teamstanding.getTeam() == team) {
				teamstanding.incrementGamesPlayed();
				teamstanding.incrementGamesLost();
			}
		}
	}

}

package com.dhl.g05.simulation.leaguesimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class StandingMockData {

	public ILeague createDummyLeague() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		ITeam team = null;
		IDivision division = null;
		IConference conference = null;
		ILeague league = modelFactory.createLeagueModel();
		List<ITeam> teamList = new ArrayList<>();
		List<IDivision> divisionList = new ArrayList<>();
		List<IConference> conferneceList = new ArrayList<>();
		for(int con=0; con<2;con++) {
			divisionList = new ArrayList<>();
			for(int div=0;div<2;div++) {
				teamList = new ArrayList<>();
				for(int teamCount = 0; teamCount < 5;teamCount++) {
					team = modelFactory.createTeamModel();
					teamList.add(team);
				}
				division = modelFactory.createDivisionModel();
				division.setTeamDetails(teamList);
				divisionList.add(division);
			}
			conference = modelFactory.createConferenceModel();
			conference.setDivisionDetails(divisionList);
			conferneceList.add(conference);
		}
		league.setConferenceDetails(conferneceList);
		return league;
	}

	public List<IStandingModel> createDummyStandings(ILeague league) {
		Random rand = new Random();
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		List<IStandingModel> standings = new ArrayList<>();
		int gamesWon = 0;

		for(IConference conference : league.getConferenceDetails()) {
			for(IDivision division : conference.getDivisionDetails()) {
				for(ITeam team : division.getTeamDetails()) {
					IStandingModel standing = simulationFactory.createStandingModel();
					standing.setConference(conference);
					standing.setDivision(division);
					standing.setTeam(team);
					standing.setTotalGamesPlayed(14);
					gamesWon = rand.nextInt(10);
					standing.setTotalGamesLost(gamesWon);
					standing.setTotalPoints(gamesWon*2);
					standings.add(standing);
				}
			}

		}

		return standings;
	}

}

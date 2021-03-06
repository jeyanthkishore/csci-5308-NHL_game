package com.dhl.g05.simulation.leaguesimulation;

import java.time.LocalDate;
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
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.google.gson.annotations.Expose;

public class LeagueSchedule implements ILeagueSchedule {
	static final Logger logger = LogManager.getLogger(LeagueSchedule.class);
	@Expose
	private List<IScheduleModel> regularSeasonSchedule;
	@Expose
	private List<IScheduleModel> playoffSeasonSchedule;

	@Override
	public List<IScheduleModel> getRegularSeasonSchedule() {
		return regularSeasonSchedule;
	}

	@Override
	public void setRegularSeasonSchedule(List<IScheduleModel> regularSchedule) {
		this.regularSeasonSchedule = regularSchedule;
	}

	@Override
	public List<IScheduleModel> getPlayoffSeasonSchedule() {
		return playoffSeasonSchedule;
	}

	@Override
	public void setPlayoffSeasonSchedule(List<IScheduleModel> playoffSchedule) {
		this.playoffSeasonSchedule = playoffSchedule;
	}

	@Override
	public void addRegularSeasonDates() {
		logger.info("Adding Dates to Regular Season");
		int totalDaysforRegualarSeason = (int)DateHandler.instance().getDaysBetweenRegularSeason();
		LocalDate regularSeasonStartDate = DateHandler.instance().getRegularSeasonStartDate();
		addDatesToSchedule(regularSeasonSchedule,totalDaysforRegualarSeason,regularSeasonStartDate);
	}

	@Override
	public void generateRegularSeasonSchedule(ILeague league) {
		logger.info("Generating Regular Season Schdeule");
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		regularSeasonSchedule = new ArrayList<>();
		playoffSeasonSchedule = null;
		List<IConference> totalConferences = new ArrayList<>();
		List<IDivision> totalDivisions = new ArrayList<>();
		List<ITeam> totalTeams = new ArrayList<>();
		int totalNumberOfTeams;

		for (IConference conference: league.getConferenceDetails()) {
			for (IDivision division: conference.getDivisionDetails()) {
				for (ITeam team: division.getTeamDetails()) {
					totalConferences.add(conference);
					totalDivisions.add(division);
					totalTeams.add(team);
				}
			}
		}

		totalNumberOfTeams = totalTeams.size();
		for (int i = 0; i < totalNumberOfTeams - 1; i++) {
			for (int j = i + 1; j < totalNumberOfTeams; j++) {
				IScheduleModel matchSchedule = simulationFactory.createScheduleModel();
				matchSchedule.setFirstConference(totalConferences.get(i));
				matchSchedule.setFirstDivision(totalDivisions.get(i));
				matchSchedule.setFirstTeam(totalTeams.get(i));
				matchSchedule.setSecondConference(totalConferences.get(j));
				matchSchedule.setSecondDivision(totalDivisions.get(j));
				matchSchedule.setSecondTeam(totalTeams.get(j));
				regularSeasonSchedule.add(matchSchedule);
			}
		}
	}

	@Override
	public void addPlayoffSeasonDates() {
		logger.info("Adding Dates to Playoff Season");
		int totalDaysforPlayoff = (int)DateHandler.instance().getDaysBetweenPlayoff();
		LocalDate playoffSeasonStartDate = DateHandler.instance().getPlayoffSeasonStartDate();
		addDatesToSchedule(playoffSeasonSchedule,totalDaysforPlayoff,playoffSeasonStartDate);
	}

	@Override
	public void generatePlayoffSchedule(ILeague league, ILeagueStanding standingSystem) {
		logger.info("Generating PlayOff Season Schdeule");
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		playoffSeasonSchedule = new ArrayList<>();
		for (IConference conference: league.getConferenceDetails()) {
			List<List<IStandingModel>> totalStandings = new ArrayList<>();
			List<List<IStandingModel>> wildCardDivision = new ArrayList<>();
			for (IDivision division: conference.getDivisionDetails()) {
				List<IStandingModel> divisionTotalStandings = standingSystem.getRankingAcrossDivision(division);
				totalStandings.add(divisionTotalStandings);
				List<IStandingModel> divisionWildCardStandings = standingSystem.getRankingAcrossDivision(division);
				wildCardDivision.add(divisionWildCardStandings);
			}

			for(int i = 0 ; i < 2 ; i++) {
				int j = 1;
				IScheduleModel matchSchedule = simulationFactory.createScheduleModel();
				matchSchedule.setFirstConference(totalStandings.get(i).get(j).getConference());
				matchSchedule.setFirstDivision(totalStandings.get(i).get(j).getDivision());
				matchSchedule.setFirstTeam(totalStandings.get(i).get(j).getTeam());
				j++;
				matchSchedule.setSecondConference(totalStandings.get(i).get(j).getConference());
				matchSchedule.setSecondDivision(totalStandings.get(i).get(j).getDivision());
				matchSchedule.setSecondTeam(totalStandings.get(i).get(j).getTeam());
				playoffSeasonSchedule.add(matchSchedule);
			}

			wildCardDivision = selectWildCardDivision(wildCardDivision);
			for(int k = 0, i =0 ; k < 2 ; k++ , i++) {
				int j = 0;
				IScheduleModel matchSchedule = simulationFactory.createScheduleModel();
				matchSchedule.setFirstConference(totalStandings.get(i).get(j).getConference());
				matchSchedule.setFirstDivision(totalStandings.get(i).get(j).getDivision());
				matchSchedule.setFirstTeam(totalStandings.get(i).get(j).getTeam());
				if(i == 1) {
					j++;
					i = 0;
				}
				matchSchedule.setSecondConference(wildCardDivision.get(i).get(j).getConference());
				matchSchedule.setSecondDivision(wildCardDivision.get(i).get(j).getDivision());
				matchSchedule.setSecondTeam(wildCardDivision.get(i).get(j).getTeam());
				playoffSeasonSchedule.add(matchSchedule);

			}
		}
	}

	private List<List<IStandingModel>> selectWildCardDivision(List<List<IStandingModel>> totalStandings) {
		logger.info("Selecting wild card teams");
		for (int i = 0; i < 3; i++) {
			totalStandings.get(0).remove(0);
			totalStandings.get(1).remove(0);
		}
		totalStandings.get(0).addAll(totalStandings.get(1));
		Collections.sort(totalStandings.get(0),StandingModel.rankingComparator);
		return totalStandings;
	}

	private void addDatesToSchedule(List<IScheduleModel> Schedule, int days,LocalDate gameDate) {
		logger.info("Adding Dates to the schedule");
		int totalGames = Schedule.size();
		int noOfGamesPerDay = (totalGames / days);
		int remainingGames = (totalGames % days);

		int scheduleIndex = 0;
		for (int i = 0; i < days; i++) {
			for (int j = 0; j < noOfGamesPerDay; j++) {
				Schedule.get(scheduleIndex++).setScheduleDate(gameDate);
			}
			if (remainingGames > 0) {
				Schedule.get(scheduleIndex++).setScheduleDate(gameDate);
				remainingGames--;
			}

			gameDate = gameDate.plusDays(1);
		}
	}


	@Override
	public boolean isGamesUnplayedOnCurrentDay(LocalDate date) {
		logger.info("Checking unplayed games on data : "+date);
		List<IScheduleModel> scheduleList = null;
		if (DateHandler.instance().isRegularSeasonActive(date)) {
			scheduleList = regularSeasonSchedule;
		} else if (DateHandler.instance().isPlayoffSeasonActive(date)) {
			scheduleList = playoffSeasonSchedule;
		} else {
			return false;
		}

		for (IScheduleModel schedule : scheduleList) {
			if (schedule.getScheduleDate().isEqual(date) && schedule.getIsGameCompleted() == false) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IScheduleModel getMatchOnCurrentDate(LocalDate date) {
		logger.info("Getting match on date :"+date);
		List<IScheduleModel> scheduleList;
		IScheduleModel currentSchedule = null;
		if (DateHandler.instance().isRegularSeasonActive(date)) {
			scheduleList = regularSeasonSchedule;
		} else {
			scheduleList = playoffSeasonSchedule;
		}

		for (IScheduleModel matchSchedule : scheduleList) {
			if (matchSchedule.getScheduleDate().isEqual(date) && matchSchedule.getIsGameCompleted() == false) {
				currentSchedule = matchSchedule;
			}
		}
		return currentSchedule;
	}

	@Override
	public boolean isStanleyCupWinnerDetermined() {
		logger.info("Checking PlayOff(StanleyCup) is Over");
		if (playoffSeasonSchedule == null) {
			return false;
		}
		for (IScheduleModel schedule: playoffSeasonSchedule) {
			if (schedule.getIsGameCompleted() == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void updateScheduleAfterGame(IScheduleModel matchSchedule) {
		logger.info("Updating Schedule after game");
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		matchSchedule.setIsGameCompleted(true);
		if (DateHandler.instance().isPlayoffSeasonActive(matchSchedule.getScheduleDate())) {
			List<Object> winningDetails = new ArrayList<>();
			int index = playoffSeasonSchedule.indexOf(matchSchedule);
			int indexTotalLength = playoffSeasonSchedule.size() - 1;
			if( index < indexTotalLength) {
				if (index % 2 == 0) {
					IScheduleModel newPlayoffGame = simulationFactory.createScheduleModel();
					LocalDate lastPlayoffDate = playoffSeasonSchedule.get(indexTotalLength).getScheduleDate();
					winningDetails = getWinDetails(matchSchedule);
					newPlayoffGame.setFirstConference((IConference) winningDetails.get(0));
					newPlayoffGame.setFirstDivision((IDivision) winningDetails.get(1));
					newPlayoffGame.setFirstTeam((ITeam) winningDetails.get(2));
					newPlayoffGame.setScheduleDate(lastPlayoffDate.plusDays(1));
					playoffSeasonSchedule.add(newPlayoffGame);
				} else {
					IScheduleModel oldSchedule = playoffSeasonSchedule.get(indexTotalLength);
					winningDetails = getWinDetails(matchSchedule);
					oldSchedule.setSecondConference((IConference) winningDetails.get(0));
					oldSchedule.setSecondDivision((IDivision) winningDetails.get(1));
					oldSchedule.setSecondTeam((ITeam) winningDetails.get(2));
				}
			}
		}
	}

	private List<Object> getWinDetails(IScheduleModel matchSchedule) {
		List<Object> dataOfWinningTeam = new ArrayList<>();
		if (matchSchedule.getWinningTeam() == matchSchedule.getFirstTeam()) {
			dataOfWinningTeam.add(matchSchedule.getFirstConference());
			dataOfWinningTeam.add(matchSchedule.getFirstDivision());
			dataOfWinningTeam.add(matchSchedule.getFirstTeam());
		}
		else {
			dataOfWinningTeam.add(matchSchedule.getSecondConference());
			dataOfWinningTeam.add(matchSchedule.getSecondDivision());
			dataOfWinningTeam.add(matchSchedule.getSecondTeam());
		}
		return dataOfWinningTeam;
	}

	@Override
	public ITeam getStanleyCupWinner() {
		logger.info("Getting Stanley Cup winner");
		int finalMatch = playoffSeasonSchedule.size() - 1;
		return playoffSeasonSchedule.get(finalMatch).getWinningTeam();
	}

}

package com.dhl.g05.leaguesimulation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

public class LeagueSchedule implements ILeagueSchedule {
	private List<IScheduleModel> regularSeasonSchedule;
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
		int totalDaysforRegualarSeason = (int)DateHandler.getInstance().getDaysBetweenRegularSeason();
		LocalDate regularSeasonStartDate = DateHandler.getInstance().getRegularSeasonStartDate();
		addDatesToSchedule(regularSeasonSchedule,totalDaysforRegualarSeason,regularSeasonStartDate);
	}

	@Override
	public void generateRegularSeasonSchedule(ILeague league) {
		regularSeasonSchedule = new ArrayList<>();
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
				IScheduleModel matchSchedule = new ScheduleModel();
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
		int totalDaysforPlayoff = (int)DateHandler.getInstance().getDaysBetweenPlayoff();
		LocalDate playoffSeasonStartDate = DateHandler.getInstance().getPlayoffSeasonStartDate();
		addDatesToSchedule(playoffSeasonSchedule,totalDaysforPlayoff,playoffSeasonStartDate);
	}

	@Override
	public void generatePlayoffSchedule(ILeague league, ILeagueStanding standingSystem) {
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
				IScheduleModel matchSchedule = new ScheduleModel();
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
				IScheduleModel matchSchedule = new ScheduleModel();
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
		for (int i = 0; i < 3; i++) {
			totalStandings.get(0).remove(0);
			totalStandings.get(1).remove(0);
		}
		totalStandings.get(0).addAll(totalStandings.get(1));
		Collections.sort(totalStandings.get(0),StandingModel.rankingComparator);
		return totalStandings;
	}

	private void addDatesToSchedule(List<IScheduleModel> Schedule, int days,LocalDate gameDate) {
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
		List<IScheduleModel> scheduleList = null;
		if (DateHandler.getInstance().isRegularSeasonActive(date)) {
			scheduleList = regularSeasonSchedule;
		} else if (DateHandler.getInstance().isPlayoffSeasonActive(date)) {
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
		List<IScheduleModel> scheduleList;
		IScheduleModel currentSchedule = null;
		if (DateHandler.getInstance().isRegularSeasonActive(date)) {
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

		matchSchedule.setIsGameCompleted(true);
		if (DateHandler.getInstance().isPlayoffSeasonActive(matchSchedule.getScheduleDate())) {
			List<Object> winningDetails = new ArrayList<>();
			int index = playoffSeasonSchedule.indexOf(matchSchedule);
			int indexTotalLength = playoffSeasonSchedule.size() - 1;
			if (index % 2 == 0) {
				IScheduleModel newPlayoffGame = new ScheduleModel();
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
		int finalMatch = playoffSeasonSchedule.size() - 1;
		return playoffSeasonSchedule.get(finalMatch).getWinningTeam();
	}

}

package com.dhl.g05.operation;

import com.dhl.g05.leaguemodel.LeagueObject;

public class CreateNewTeam {

	private LeagueObject leagueObject;
	
	public CreateNewTeam() {
		setLeagueObject(null);
	}
	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}
	
	public LeagueObject getLeagueObject() {
		return leagueObject;
	}

	public CreateNewTeam(LeagueObject league) {
		this.leagueObject = league;
	}

	public boolean isTeamExist(String teamName) {
		Boolean teamPresent = leagueObject.getConferenceDetails().stream()
				.anyMatch(v->v.getDivisionDetails().parallelStream().anyMatch(p->p.getTeamDetails().stream()
						.anyMatch(r->r.getTeamName().equalsIgnoreCase(teamName))));
		return teamPresent;
	}
	public boolean isConferenceExist(String conferenceName) {
		Boolean conferencePresent = leagueObject.getConferenceDetails().stream().anyMatch
		(p->p.getConferenceName().equalsIgnoreCase(conferenceName));
		return conferencePresent;
	}
	public boolean isDivisionExist(String divisionName) {
		 Boolean divisionPresent= leagueObject.getConferenceDetails().parallelStream().anyMatch(v->v.getDivisionDetails().stream().anyMatch
				 (p->p.getDivisionName().equalsIgnoreCase(divisionName)));
		 return divisionPresent;
	}
	
}

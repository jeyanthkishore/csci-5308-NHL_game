package com.dhl.g05.model;

import java.util.List;

public interface IGenerateNewPlayers {

	public void setNumberOfTeams(int numberOfTeams);

	public int getNumberOfTeams();

	public String generateRandomName();

	public int[] generatePlayerBirthdate();

	public List<IPlayer> generatePlayers();

}

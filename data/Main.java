package data;

import data.DataLayer;
import data.Teams;
import entities.Team;

public class Main {

	public static void main(String[] args) {
		DataLayer dataLayer = new DataLayer();
		
		Teams teams = new Teams(dataLayer.getConnection());
		Team team1 = new Team("Herning IF");		
		
		teams.createTeam(team1);
		teams.readTeamById(1);
		System.out.println("Success!");
	}
}

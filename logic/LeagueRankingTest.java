package logic;

import java.util.ArrayList;

import data.DatabaseController;
import entities.Team;

public class LeagueRankingTest {
	private static DatabaseController dbController = new DatabaseController();
	
	public static void main(String[] args) {
		Team team1 = new Team(1, "Ikast FC", 26);         
		Team team2 = new Team(2, "Herning FC", 23);         
		Team team3 = new Team(3, "Viborg FC", 20);         
		Team team4 = new Team(4, "Vejle FC", 24);         
	    ArrayList<Team> teamsList = new ArrayList<>();         
	    teamsList.add(team1);         
	    teamsList.add(team2);         
	    teamsList.add(team3);         
	    teamsList.add(team4);         
	    
	    LeagueRanking teamPointsSorter = new LeagueRanking(teamsList);         
	    ArrayList<Team> teamPointsSorted = teamPointsSorter.getSortedTeamsByTeamPoints();
	    
	    for (int i = 0; i < teamsList.size(); i++) {
			teamsList.get(i).setRanking(i);
		}
	    for (Team team : teamPointsSorted) {             
	      System.out.println(team);         
	    }      
	}

}

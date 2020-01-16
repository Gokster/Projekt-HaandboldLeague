package logic;

import java.util.ArrayList;
import java.util.Collections;

import entities.Team;

public class LeagueRanking {
	private ArrayList<Team> teamsList = new ArrayList<>();
         
  public LeagueRanking(ArrayList<Team> teamsList) {         
    this.teamsList = teamsList;     
  }       
  public ArrayList<Team> getSortedTeamsByTeamPoints() {         
    Collections.sort(teamsList, Collections.reverseOrder());         
    return teamsList;     
  } 
}



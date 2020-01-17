package logic;

import java.util.ArrayList;
import java.util.Collections;

import entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeagueRanking {
	private ObservableList<Team> teamsList = FXCollections.observableArrayList();
         
  public LeagueRanking(ObservableList<Team> teams) {         
    this.teamsList = teams;     
  }       
  public ObservableList<Team> getSortedTeamsByTeamPoints() {         
    Collections.sort(teamsList, Collections.reverseOrder());         
    return teamsList;     
  } 
}



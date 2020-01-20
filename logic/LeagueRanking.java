package logic;

import java.util.ArrayList;
import java.util.Collections;

import entities.Team;
import javafx.collections.ObservableList;

public class LeagueRanking {
	public ObservableList<Team> LeagueRanker(ObservableList<Team> teamsList){
		Collections.sort(teamsList, Collections.reverseOrder());
		
		//Sets ranks for all teams
		for (int i = 0; i < teamsList.size(); i++) {
			teamsList.get(i).setRanking(i+1);
		}
		return teamsList;
	}
}

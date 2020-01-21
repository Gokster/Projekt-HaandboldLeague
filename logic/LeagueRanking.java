package logic;

import java.util.Collections;

import entities.Team;
import javafx.collections.ObservableList;

public class LeagueRanking {
	public ObservableList<Team> LeagueRanker(ObservableList<Team> teamsList){
		Collections.sort(teamsList, Collections.reverseOrder());
		
		for (int i = 0; i < teamsList.size(); i++) {
			teamsList.get(i).setRanking(i+1);
		}
		return teamsList;
	}
}
